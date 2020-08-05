package com.undcon.app.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.dtos.ProductSaledInfoDto;
import com.undcon.app.dtos.SaleIncomeRequestDto;
import com.undcon.app.dtos.SaleIncomeResponseDto;
import com.undcon.app.dtos.SaleInfoDto;
import com.undcon.app.dtos.SaleItemDto;
import com.undcon.app.dtos.SaleRequestDto;
import com.undcon.app.dtos.SaleTotalDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.SaleStatus;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.IncomeEntity;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemProductEntity;
import com.undcon.app.model.SaleItemServiceEntity;
import com.undcon.app.model.ServiceTypeEntity;
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
	private ISaleItemProductRepository saleItemProductRepository;

	@Autowired
	private ISaleItemServiceRepository saleItemServiceRepository;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private StockService stockService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private IncomeService incomeService;

	public Page<SaleEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(SaleEntity.class, filter, page, size);
	}

	public SaleInfoDto getTotalSale() {
		return new SaleInfoDto(saleRepositoryImpl.getTotalSale(true), saleRepositoryImpl.getTotalSale(false));
	}

	@Transactional
	public SaleEntity persist(SaleRequestDto saleDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		UserEntity user = userService.getCurrentUser();

		CustomerEntity customer = customerService.findById(saleDto.getCustomer().getId());
		validateClient(customer);
		boolean billed = false;
		Date saleDate = new Date(System.currentTimeMillis());
		SaleStatus status = SaleStatus.CREATED;

		EmployeeEntity salesman = user.getEmployee();
		// Se o Front não enviar o funcionário
		if (saleDto.getSalesman() != null && NumberUtils.longIsPositiveValue(saleDto.getSalesman().getId())) {
			salesman = employeeService.findById(saleDto.getSalesman().getId());
		}
		SaleEntity sale = new SaleEntity(null, customer, saleDate, billed, status, user, salesman);
		return saleRepository.save(sale);
	}

	private void validateClient(CustomerEntity customer) throws UndconException {
		if (customer == null) {
			throw new UndconException(UndconError.SALE_ENTITY_INVALID_CLIENT);
		}
	}

	@Transactional
	public SaleEntity update(SaleRequestDto saleDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = findById(saleDto.getId());

		CustomerEntity customer = customerService.findById(saleDto.getCustomer().getId());
		validateClient(customer);
		return saleRepository.save(sale);
	}

	@Transactional
	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = findById(id);
		sale.setStatus(SaleStatus.CANCELED);
		saleRepository.save(sale);
	}

	@Transactional
	public SaleItemEntity addItemProduct(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		ProductEntity product = productService.findById(itemDto.getItemId());

		if (product == null) {
			throw new UndconException(UndconError.PRODUCT_NOT_FOUND);
		}
		stockService.checkStockAvaiable(product, itemDto.getQuantity());

		UserEntity user = userService.getCurrentUser();

		EmployeeEntity employee = user.getEmployee();

		// Se o Front não enviar o funciona´rio
		if (NumberUtils.longIsPositiveValue(itemDto.getEmployeeId())) {
			employee = employeeService.findById(itemDto.getEmployeeId());
		}

		SaleItemProductEntity item = new SaleItemProductEntity(null, product, sale, user, employee,
				product.getSalePrice(), itemDto.getQuantity());
		item = saleItemProductRepository.save(item);

		stockService.discounProductOfStock(product, itemDto.getQuantity());

		return item;
	}

	@Transactional
	public SaleItemEntity addItemService(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		ServiceTypeEntity service = serviceTypeService.findById(itemDto.getItemId());
		if (service == null) {
			throw new UndconException(UndconError.SERVICE_TYPE_NOT_FOUND);
		}

		UserEntity user = userService.getCurrentUser();

		EmployeeEntity employee = user.getEmployee();

		// Se o Front não enviar o funciona´rio
		if (NumberUtils.longIsPositiveValue(itemDto.getEmployeeId())) {
			employee = employeeService.findById(itemDto.getEmployeeId());
		}

		SaleItemServiceEntity item = new SaleItemServiceEntity(null, service, sale, user, employee, service.getPrice(),
				itemDto.getQuantity());

		return saleItemServiceRepository.save(item);
	}

	@Transactional
	public SaleItemEntity updateProductItem(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}
		ProductEntity product = productService.findById(itemDto.getItemId());

		SaleItemProductEntity item = saleItemProductRepository.findOne(itemDto.getId());

		product.setStock(product.getStock() + item.getQuantity());

		stockService.checkStockAvaiable(product, itemDto.getQuantity());

		saleItemProductRepository.save(item);

		stockService.discounProductOfStock(product, itemDto.getQuantity());

		return item;
	}

	@Transactional
	public SaleItemEntity updateServiceItem(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}
		UserEntity user = userService.getCurrentUser();
		ServiceTypeEntity service = serviceTypeService.findById(itemDto.getItemId());

		SaleItemServiceEntity item = saleItemServiceRepository.findOne(itemDto.getId());
		item.setService(service);
		item.setPrice(item.getPrice());
		item.setUser(user);

		return saleItemServiceRepository.save(item);
	}

	@Transactional
	public void deleteProductItem(Long saleId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		SaleItemProductEntity item = saleItemProductRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND);
		}

		if (!item.getSale().getId().equals(sale.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		saleItemProductRepository.delete(item);
	}

	public void deleteServiceItem(Long saleId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		SaleItemServiceEntity item = saleItemServiceRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND);
		}

		if (!item.getSale().getId().equals(sale.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		saleItemServiceRepository.delete(item);
	}

	public List<ProductSaledInfoDto> getTopProductSaled(boolean billed) {
		return saleRepositoryImpl.getTopProductSaled(billed);
	}

	public Page<SaleItemDto> getItens(Long id, Integer page, Integer size) {
		return saleRepositoryImpl.findAllById(id, PageUtils.createPageRequest(page, size));
	}

	public SaleTotalDto getSaleTotal(Long id) {
		return saleRepositoryImpl.getSaleTotal(id);
	}

	@Override
	protected JpaRepository<SaleEntity, Long> getRepository() {
		return saleRepository;
	}

	@Transactional
	public SaleIncomeResponseDto toBill(long id, SaleIncomeRequestDto saleIncomeDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = findById(id);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		Date paymentDate = saleIncomeDto.getPaymentDate();
		if(saleIncomeDto.isSettled() && paymentDate == null) {
			paymentDate = new Date(System.currentTimeMillis());
		}
		
		IncomeEntity income = new IncomeEntity(null, "Pagamento de Venda - " + sale.getSaleDate() + " #" + sale.getId(),
				saleIncomeDto.getDuaDate(), paymentDate, saleIncomeDto.getValue(),
				saleIncomeDto.isSettled(), sale, sale.getCustomer(), saleIncomeDto.getPaymentType());
		incomeService.persist(income);

		sale.setStatus(SaleStatus.BILLED);

		sale = saleRepository.save(sale);

		double amountPayable = 0;
		double amountPaid = 0;

		amountPayable = new BigDecimal(amountPayable).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		amountPaid = new BigDecimal(amountPaid).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		return new SaleIncomeResponseDto(income, sale, amountPayable, amountPaid);
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SALE;
	}

}
