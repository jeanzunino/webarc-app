package com.undcon.app.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.undcon.app.dtos.IncomeDto;
import com.undcon.app.dtos.ItemDashboardInfoDto;
import com.undcon.app.dtos.SaleIncomeRequestDto;
import com.undcon.app.dtos.SaleIncomeResponseDto;
import com.undcon.app.dtos.SaleInfoDto;
import com.undcon.app.dtos.SaleItemDto;
import com.undcon.app.dtos.SaleRequestDto;
import com.undcon.app.dtos.SaleSimpleDto;
import com.undcon.app.dtos.ValueByInterval;
import com.undcon.app.dtos.AmountTotalDto;
import com.undcon.app.enums.BillingStatus;
import com.undcon.app.enums.IntervalType;
import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.PaymentType;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.IncomeMapper;
import com.undcon.app.mappers.SaleMapper;
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.IncomeEntity;
import com.undcon.app.model.PdvEntity;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.ISaleItemProductRepository;
import com.undcon.app.repositories.ISaleItemServiceRepository;
import com.undcon.app.repositories.ISaleRepository;
import com.undcon.app.repositories.SaleRepositoryImpl;
import com.undcon.app.utils.NumberUtils;
import com.undcon.app.utils.PageUtils;

@Component
public class SaleService extends AbstractService<SaleEntity> {

	@Autowired
	private ISaleRepository saleRepository;

	@Autowired
	private SaleRepositoryImpl saleRepositoryImpl;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private SaleMapper saleMapper;

	@Autowired
	private IncomeMapper incomeMapper;

	@Autowired
	private BankCheckService bankCheckService;

	@Autowired
	private ISaleItemProductRepository saleItemProductRepository;

	@Autowired
	private ISaleItemServiceRepository saleItemServiceRepository;

	public Page<SaleEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(SaleEntity.class, filter, page, size);
	}

	public SaleInfoDto getTotalSale() {
		return new SaleInfoDto(saleRepositoryImpl.getTotalSale(true), saleRepositoryImpl.getTotalSale(false));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SaleEntity persist(SaleRequestDto saleDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		UserEntity user = userService.getCurrentUser();

		CustomerEntity customer = customerService.findById(saleDto.getCustomer().getId());
		validateClient(customer);
		boolean billed = false;
		Date saleDate = new Date(System.currentTimeMillis());
		BillingStatus status = BillingStatus.CREATED;

		EmployeeEntity salesman = user.getEmployee();
		// Se o Front não enviar o funcionário
		if (saleDto.getSalesman() != null && NumberUtils.longIsPositiveValue(saleDto.getSalesman().getId())) {
			salesman = employeeService.findById(saleDto.getSalesman().getId());
		}
		PdvEntity pdv = null;
		SaleEntity sale = new SaleEntity(null, customer, saleDate, billed, status, user, salesman, pdv);
		return saleRepository.save(sale);
	}

	public boolean hasItem(SaleEntity sale) {
		if (saleItemProductRepository.existsBySale(sale)) {
			return true;
		}
		return saleItemServiceRepository.existsBySale(sale);
	}

	private void validateClient(CustomerEntity customer) throws UndconException {
		if (customer == null) {
			throw new UndconException(UndconError.SALE_ENTITY_INVALID_CLIENT);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SaleEntity update(SaleRequestDto saleDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = findById(saleDto.getId());

		// TODO falta ajustar o Frontend pra chamar outro serviço para finalizar a venda
//		if(sale.getStatus() != saleDto.getStatus()) {
//			throw new UndconException(UndconError.SALE_INVALID_STATUS);
//		}
		CustomerEntity customer = customerService.findById(saleDto.getCustomer().getId());
		validateClient(customer);

		EmployeeEntity salesman;
		if (saleDto.getSalesman() != null && NumberUtils.longIsPositiveValue(saleDto.getSalesman().getId())) {
			salesman = employeeService.findById(saleDto.getSalesman().getId());
		} else {
			UserEntity user = userService.getCurrentUser();
			salesman = user.getEmployee();
		}

		sale.setCustomer(customer);
		sale.setSalesman(salesman);
		sale.setStatus(saleDto.getStatus());

		return saleRepository.save(sale);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = findById(id);
		sale.setStatus(BillingStatus.CANCELED);
		saleRepository.save(sale);
	}

	public List<ItemDashboardInfoDto> getTopProductSaled(String startDate, String endDate, int size) {
		return saleRepositoryImpl.getTopProductSaled(startDate, endDate,size);
	}

	public Page<SaleItemDto> getItens(Long id, Integer page, Integer size) {
		return saleRepositoryImpl.findAllById(id, PageUtils.createPageRequest(page, size));
	}

	public AmountTotalDto getSaleTotal(Long id) throws UndconException {
		SaleEntity sale = findById(id);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}
		Double saleTotal = saleRepositoryImpl.getSaleTotal(id);
		double amountPaid = incomeService.getIncomeValueBilledBySale(sale);
		double amountPayable = saleTotal - amountPaid;
		amountPayable = new BigDecimal(amountPayable).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		return new AmountTotalDto(saleTotal, amountPayable, amountPaid);
	}

	@Override
	protected JpaRepository<SaleEntity, Long> getRepository() {
		return saleRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SaleIncomeResponseDto toBill(long id, List<SaleIncomeRequestDto> saleIncomeDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		List<IncomeDto> incomes = new ArrayList<IncomeDto>();
		SaleIncomeResponseDto dtoResponse = null;
		for (SaleIncomeRequestDto saleIncomeRequestDto : saleIncomeDto) {
			dtoResponse = toBill(id, saleIncomeRequestDto);
			incomes.addAll(dtoResponse.getIncomesCreated());
		}
		dtoResponse.setIncomesCreated(incomes);
		return dtoResponse;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SaleIncomeResponseDto toBill(long id, SaleIncomeRequestDto saleIncomeDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(id);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		if (sale.getStatus() == BillingStatus.TOTAL_BILLED) {
			throw new UndconException(UndconError.SALE_INCOME_TO_BILL_SALE_TOTAL_BILLED);
		}

		if (sale.getStatus() == BillingStatus.CANCELED) {
			throw new UndconException(UndconError.SALE_INCOME_CANCELED_SALE);
		}

		Date paymentDate = saleIncomeDto.getPaymentDate();
		if (saleIncomeDto.isSettled() && paymentDate == null) {
			paymentDate = new Date(System.currentTimeMillis());
		}

		if (saleIncomeDto.getPaymentType() == PaymentType.BANK_CHECK) {
			Assert.notNull(saleIncomeDto.getCheck(), "check is required");
			bankCheckService.saveBankCheck(saleIncomeDto.getCheck());
		}

		PaymentStatus paymentStatus = saleIncomeDto.isSettled() ? PaymentStatus.SETTLED : PaymentStatus.PENDING;
		IncomeEntity income = new IncomeEntity(null, "Pagamento de Venda - " + sale.getSaleDate() + " #" + sale.getId(),
				saleIncomeDto.getDuaDate(), paymentDate, saleIncomeDto.getValue(), paymentStatus, sale,
				sale.getCustomer(), saleIncomeDto.getPaymentType());

		// TODO Verificar se o usuário precisará de permissão para gravar Venda e
		// Receita para realizar o Pagamento da Venda. Hoje é necessário.
		incomeService.persist(income);

		double amountPaid = incomeService.getIncomeValueBilledBySale(sale);
		Double totalValueSale = getSaleTotal(sale.getId()).getTotalValue();

		if (amountPaid > totalValueSale) {
			// Não temos juros na venda, por isso hoje o valor total da venda e das receitas
			// sempre é soma dos valores dos itens da venda
			throw new UndconException(UndconError.SALE_INCOME_AMOUNT_PAID_IS_MAJOR_SALE_TOTAL_VALUE);
		}
		double amountPayable = totalValueSale - amountPaid;

		if (amountPayable > 0) {
			sale.setStatus(BillingStatus.BILLED);
		} else {
			sale.setStatus(BillingStatus.TOTAL_BILLED);
		}

		sale = saleRepository.save(sale);

		amountPayable = new BigDecimal(amountPayable).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		amountPaid = new BigDecimal(amountPaid).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

		IncomeDto incomeDto = incomeMapper.toDto(income);
		SaleSimpleDto saleDto = saleMapper.toSimpleDto(sale);
		return new SaleIncomeResponseDto(Arrays.asList(incomeDto), saleDto, amountPayable, amountPaid);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SaleSimpleDto toCancel(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(id);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		List<IncomeEntity> incomesBySale = incomeService.getIncomesBySale(sale);
		for (IncomeEntity incomeEntity : incomesBySale) {
			incomeEntity.setPaymentStatus(PaymentStatus.CANCELED);
			incomeService.update(incomeEntity);
		}

		sale.setStatus(BillingStatus.CANCELED);

		sale = saleRepository.save(sale);

		SaleSimpleDto saleDto = saleMapper.toSimpleDto(sale);
		return saleDto;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SaleSimpleDto finalize(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(id);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		if (!hasItem(sale)) {
			throw new UndconException(UndconError.SALE_WITHOUT_ITENS_INVALID_TO_BILL);
		}
		sale.setStatus(BillingStatus.TO_BILL);
		return saleMapper.toSimpleDto(sale);
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SALE;
	}

	public List<ValueByInterval> getTotalSaledProductByInterval(String startDate, String endDate, IntervalType type) {
		return saleRepositoryImpl.getTotalSaledProductByInterval(startDate, endDate, type);
	}
	
	public List<ValueByInterval> getTotalSaledServiceByInterval(String startDate, String endDate, IntervalType type) {
		return saleRepositoryImpl.getTotalSaledServiceByInterval(startDate, endDate, type);
	}

}
