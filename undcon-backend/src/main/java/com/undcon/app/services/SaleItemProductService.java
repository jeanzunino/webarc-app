package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemProductEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.ISaleItemProductRepository;
import com.undcon.app.utils.NumberUtils;

@Component
public class SaleItemProductService extends AbstractService<SaleItemProductEntity> {

	@Autowired
	private ISaleItemProductRepository saleItemProductRepository;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public SaleItemEntity addItemProduct(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = saleService.findById(saleId);
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
	public SaleItemEntity updateProductItem(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = saleService.findById(saleId);
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
	public void deleteProductItem(Long saleId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = saleService.findById(saleId);
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
	
	@Override
	protected JpaRepository<SaleItemProductEntity, Long> getRepository() {
		return saleItemProductRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SALE;
	}

}
