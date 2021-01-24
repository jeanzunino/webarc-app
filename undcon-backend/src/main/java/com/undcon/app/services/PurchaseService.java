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

import com.undcon.app.dtos.ExpenseDto;
import com.undcon.app.dtos.PurchaseExpenseRequestDto;
import com.undcon.app.dtos.PurchaseExpenseResponseDto;
import com.undcon.app.dtos.PurchaseItemDto;
import com.undcon.app.dtos.PurchaseRequestDto;
import com.undcon.app.dtos.PurchaseSimpleDto;
import com.undcon.app.dtos.ValueByInterval;
import com.undcon.app.dtos.AmountTotalDto;
import com.undcon.app.enums.BillingStatus;
import com.undcon.app.enums.IntervalType;
import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.PaymentType;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.ExpenseMapper;
import com.undcon.app.mappers.PurchaseMapper;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.model.ProviderEntity;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPurchaseItemProductRepository;
import com.undcon.app.repositories.IPurchaseItemServiceRepository;
import com.undcon.app.repositories.IPurchaseRepository;
import com.undcon.app.repositories.PurchaseRepositoryImpl;
import com.undcon.app.utils.PageUtils;

@Component
public class PurchaseService extends AbstractService<PurchaseEntity> {

	@Autowired
	private IPurchaseRepository purchaseRepository;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private ProviderService providerService;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private IPurchaseItemProductRepository purchaseItemProductRepository;

	@Autowired
	private IPurchaseItemServiceRepository purchaseItemServiceRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BankCheckService bankCheckService;

	@Autowired
	private PurchaseRepositoryImpl purchaseRepositoryImpl;

	@Autowired
	private ExpenseMapper expenseMapper;

	@Autowired
	private PurchaseMapper purchaseMapper;

	public Page<PurchaseEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(PurchaseEntity.class, filter, page, size);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseEntity persist(PurchaseRequestDto purchase) throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);

		UserEntity user = userService.getCurrentUser();

		ProviderEntity provider = providerService.findById(purchase.getProvider().getId());
		validateProvider(provider);
		boolean billed = false;
		Date saleDate = new Date(System.currentTimeMillis());
		BillingStatus status = BillingStatus.CREATED;

		PurchaseEntity purchaseEntity = new PurchaseEntity(null, provider, saleDate, billed, status, user);
		return purchaseRepository.save(purchaseEntity);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseEntity update(PurchaseRequestDto purchaseDto) throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);
		PurchaseEntity purchase = findById(purchaseDto.getId());

		ProviderEntity provider = providerService.findById(purchaseDto.getProvider().getId());
		validateProvider(provider);

		purchase.setProvider(provider);

		return purchaseRepository.save(purchase);
	}

	@Override
	protected void validateBeforeDelete(PurchaseEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
		entity.setStatus(BillingStatus.CANCELED);
		purchaseRepository.save(entity);
	}

	private void validateProvider(ProviderEntity entity) throws UndconException {
		if (entity == null) {
			throw new UndconException(UndconError.PURCHASE_ENTITY_INVALID_PROVIDER);
		}
	}

	public Page<PurchaseItemDto> getItens(Long id, Integer page, Integer size) {
		return purchaseRepositoryImpl.findAllById(id, PageUtils.createPageRequest(page, size));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseExpenseResponseDto toBill(long id, List<PurchaseExpenseRequestDto> purchaseExpenseDtoList)
			throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);
		List<ExpenseDto> expenses = new ArrayList<ExpenseDto>();
		PurchaseExpenseResponseDto dtoResponse = null;
		for (PurchaseExpenseRequestDto purchaseExpenseDto : purchaseExpenseDtoList) {
			dtoResponse = toBill(id, purchaseExpenseDto);
			expenses.addAll(dtoResponse.getExpensesCreated());
		}
		dtoResponse.setExpensesCreated(expenses);
		return dtoResponse;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseExpenseResponseDto toBill(long id, PurchaseExpenseRequestDto purchaseExpenseDto)
			throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);

		PurchaseEntity purchase = findById(id);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		if (purchase.getStatus() == BillingStatus.TOTAL_BILLED) {
			throw new UndconException(UndconError.SALE_INCOME_TO_BILL_SALE_TOTAL_BILLED);
		}

		if (purchase.getStatus() == BillingStatus.CANCELED) {
			throw new UndconException(UndconError.SALE_INCOME_CANCELED_SALE);
		}

		Date paymentDate = purchaseExpenseDto.getPaymentDate();
		if (purchaseExpenseDto.isSettled() && paymentDate == null) {
			paymentDate = new Date(System.currentTimeMillis());
		}

		if (purchaseExpenseDto.getPaymentType() == PaymentType.BANK_CHECK) {
			Assert.notNull(purchaseExpenseDto.getCheck(), "check is required");
			bankCheckService.saveBankCheck(purchaseExpenseDto.getCheck());
		}

		PaymentStatus paymentStatus = purchaseExpenseDto.isSettled() ? PaymentStatus.SETTLED : PaymentStatus.PENDING;
		ExpenseEntity expense = new ExpenseEntity(null,
				"Pagamento de Compra - " + purchase.getPurchaseDate() + " #" + purchase.getId(),
				purchaseExpenseDto.getDuaDate(), paymentDate, purchaseExpenseDto.getValue(), paymentStatus,
				purchase.getProvider(), purchaseExpenseDto.getPaymentType(), purchase);

		// TODO Verificar se o usuário precisará de permissão para gravar Venda e
		// Receita para realizar o Pagamento da Venda. Hoje é necessário.
		expense = expenseService.persist(expense);

		double amountPaid = expenseService.getExpenseValueBilledByPurchase(purchase);
		Double totalValueSale = getPurchaseTotal(purchase.getId()).getTotalValue();

		if (amountPaid > totalValueSale) {
			// Não temos juros na compra, por isso hoje o valor total da compra e das
			// despesas
			// sempre é soma dos valores dos itens da compra
			throw new UndconException(UndconError.SALE_INCOME_AMOUNT_PAID_IS_MAJOR_SALE_TOTAL_VALUE);
		}
		double amountPayable = totalValueSale - amountPaid;

		if (amountPayable > 0) {
			purchase.setStatus(BillingStatus.BILLED);
		} else {
			purchase.setStatus(BillingStatus.TOTAL_BILLED);
		}

		purchase = purchaseRepository.save(purchase);

		amountPayable = new BigDecimal(amountPayable).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		amountPaid = new BigDecimal(amountPaid).setScale(2, RoundingMode.HALF_EVEN).doubleValue();

		ExpenseDto expenseDto = expenseMapper.toDto(expense);
		PurchaseSimpleDto purchaseDto = purchaseMapper.toSimpleDto(purchase);
		return new PurchaseExpenseResponseDto(Arrays.asList(expenseDto), purchaseDto, amountPayable, amountPaid);
	}

	public AmountTotalDto getPurchaseTotal(Long id) throws UndconException {
		PurchaseEntity purchase = findById(id);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}
		Double saleTotal = purchaseRepositoryImpl.getPurchaseTotal(id);
		double amountPaid = expenseService.getExpenseValueBilledByPurchase(purchase);
		double amountPayable = saleTotal - amountPaid;
		amountPayable = new BigDecimal(amountPayable).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		return new AmountTotalDto(saleTotal, amountPayable, amountPaid);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseSimpleDto toCancel(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		PurchaseEntity purchase = findById(id);
		if (purchase == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		List<ExpenseEntity> expensesBySale = expenseService.getExpensesByPurchase(purchase);
		for (ExpenseEntity expenseEntity : expensesBySale) {
			expenseEntity.setPaymentStatus(PaymentStatus.CANCELED);
			expenseService.update(expenseEntity);
		}

		purchase.setStatus(BillingStatus.CANCELED);

		purchase = purchaseRepository.save(purchase);

		PurchaseSimpleDto purchaseDto = purchaseMapper.toSimpleDto(purchase);
		return purchaseDto;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PurchaseSimpleDto finalize(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);

		PurchaseEntity purchase = findById(id);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		if (!hasItem(purchase)) {
			throw new UndconException(UndconError.SALE_WITHOUT_ITENS_INVALID_TO_BILL);
		}
		purchase.setStatus(BillingStatus.TO_BILL);
		return purchaseMapper.toSimpleDto(purchase);
	}

	public List<ValueByInterval> getTotalPurchasedProductByInterval(String startDate, String endDate, IntervalType type) {
		return purchaseRepositoryImpl.getTotalPurchasedProductByInterval(startDate, endDate, type);
	}
	
	public List<ValueByInterval> getTotalPurchasedServiceByInterval(String startDate, String endDate, IntervalType type) {
		return purchaseRepositoryImpl.getTotalPurchasedServiceByInterval(startDate, endDate, type);
	}
	
	public boolean hasItem(PurchaseEntity purchase) {
		if (purchaseItemProductRepository.existsByPurchase(purchase)) {
			return true;
		}
		return purchaseItemServiceRepository.existsByPurchase(purchase);
	}

	@Override
	protected JpaRepository<PurchaseEntity, Long> getRepository() {
		return purchaseRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.PURCHASE;
	}
}
