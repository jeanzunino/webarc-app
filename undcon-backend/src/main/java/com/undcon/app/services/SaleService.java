package com.undcon.app.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ProductItemRequestDto;
import com.undcon.app.dtos.SaleRequestDto;
import com.undcon.app.enums.ResourseType;
import com.undcon.app.enums.SaleStatus;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemProductEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.ISaleItemRepository;
import com.undcon.app.repositories.ISaleRepository;
import com.undcon.app.utils.LongUtils;
import com.undcon.app.utils.PageUtils;

@Component
public class SaleService {

	@Autowired
	private ISaleRepository saleRepository;

	@Autowired
	private ISaleItemRepository saleItemRepository;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private StockService stockService;

	@Autowired
	private EmployeeService employeeService;

	public List<SaleEntity> getAll(Integer page, Integer size) {
		return saleRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
	}

	public SaleEntity findById(Long id) {
		return saleRepository.findOne(id);
	}

	public SaleEntity persist(SaleRequestDto saleDto) throws UndconException {
		permissionService.checkPermission(ResourseType.SALE);

		UserEntity user = userService.getCurrentUser();

		CustomerEntity customer = customerService.findById(saleDto.getCustomerId());
		validateClient(customer);
		boolean billed = false;
		Date saleDate = new Date(System.currentTimeMillis());
		SaleStatus status = SaleStatus.CREATED;
		
		EmployeeEntity salesman = user.getEmployee();
		// Se o Front não enviar o funciona´rio
		if (LongUtils.longIsPositiveValue(saleDto.getSalesmanId())) {
			salesman = employeeService.findById(saleDto.getSalesmanId());
		}
		SaleEntity sale = new SaleEntity(null, customer, saleDate, billed, status, user, salesman);
		return saleRepository.save(sale);
	}

	private void validateClient(CustomerEntity customer) throws UndconException {
		if (customer == null) {
			throw new UndconException(UndconError.SALE_ENTITY_INVALID_CLIENT);
		}
	}

	public SaleEntity update(SaleRequestDto saleDto) throws UndconException {
		permissionService.checkPermission(ResourseType.SALE);
		SaleEntity sale = findById(saleDto.getId());

		CustomerEntity customer = customerService.findById(saleDto.getCustomerId());
		validateClient(customer);
		return saleRepository.save(sale);
	}

	public void delete(long id) {
		permissionService.checkPermission(ResourseType.SALE);
		SaleEntity sale = findById(id);
		sale.setStatus(SaleStatus.CANCELED);
		saleRepository.save(sale);
	}

	@Transactional
	public SaleItemEntity addItem(long saleId, ProductItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourseType.SALE);
		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		ProductEntity product = productService.findById(itemDto.getProductId());

		stockService.checkStockAvaiable(product, itemDto.getQuantity());

		UserEntity user = userService.getCurrentUser();

		EmployeeEntity employee = user.getEmployee();

		// Se o Front não enviar o funciona´rio
		if (LongUtils.longIsPositiveValue(itemDto.getSalesmanId())) {
			employee = employeeService.findById(itemDto.getSalesmanId());
		}

		SaleItemProductEntity item = new SaleItemProductEntity(null, product, sale, user, employee,
				product.getSalePrice(), itemDto.getQuantity());
		saleItemRepository.save(item);

		stockService.updateStock(product, itemDto.getQuantity());

		return item;
	}

	public SaleItemEntity updateItem(long saleId, ProductItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourseType.SALE);

		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}
		ProductEntity product = productService.findById(itemDto.getProductId());

		SaleItemEntity item = saleItemRepository.findOne(itemDto.getId());

		product.setStock(product.getStock() + item.getQuantity());

		stockService.checkStockAvaiable(product, itemDto.getQuantity());

		saleItemRepository.save(item);

		stockService.updateStock(product, itemDto.getQuantity());

		return item;
	}

	public void deleteItem(Long saleId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourseType.SALE);

		SaleEntity sale = findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		SaleItemEntity item = saleItemRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND);
		}

		if (!item.getSale().getId().equals(sale.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		saleItemRepository.delete(item);
	}
}
