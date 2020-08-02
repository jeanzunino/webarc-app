package com.undcon.app.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ProductItemRequestDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.SaleStatus;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.PurchaseItemEntity;
import com.undcon.app.model.PurchaseItemProductEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPurchaseItemRepository;
import com.undcon.app.repositories.IPurchaseRepository;
import com.undcon.app.utils.NumberUtils;


@Component
public class PurchaseService extends AbstractService<PurchaseEntity>{

	@Autowired
	private IPurchaseRepository purchaseRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockService stockService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private IPurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	private UserService userService;

	public Page<PurchaseEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(PurchaseEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(PurchaseEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		entity.setStatus(SaleStatus.CREATED);
		entity.setPurchaseDate(new Date(System.currentTimeMillis()));
		entity.setBilled(false);
		entity.setUser(userService.getCurrentUser());
		validateProvider(entity);
	}

	@Override
	protected void validateBeforeUpdate(PurchaseEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateProvider(entity);
	}

	@Override
	protected void validateBeforeDelete(PurchaseEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
		entity.setStatus(SaleStatus.CANCELED);
		purchaseRepository.save(entity);
	}
	
	private void validateProvider(PurchaseEntity entity) throws UndconException {
		if(entity.getProvider() == null) {
			throw new UndconException(UndconError.PURCHASE_ENTITY_INVALID_PROVIDER);
		}
	}
	
	@Transactional
	public PurchaseItemEntity addItem(long purchaseId, ProductItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		PurchaseEntity purchase = findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		ProductEntity product = productService.findById(itemDto.getProductId());

		stockService.checkStockAvaiable(product, itemDto.getQuantity());

		UserEntity user = userService.getCurrentUser();

		EmployeeEntity employee = user.getEmployee();

		// Se o Front não enviar o funciona´rio
		if (NumberUtils.longIsPositiveValue(itemDto.getEmployeeId())) {
			employee = employeeService.findById(itemDto.getEmployeeId());
		}

		PurchaseItemProductEntity item = new PurchaseItemProductEntity(null, product, purchase, user, employee,
				product.getSalePrice(), itemDto.getQuantity());
		purchaseItemRepository.save(item);

		stockService.discounProductOfStock(product, itemDto.getQuantity());

		return item;
	}

	@Transactional
	public PurchaseItemEntity updateItem(long saleId, ProductItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		PurchaseEntity purchase = findById(saleId);
		if (purchase == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}
		ProductEntity product = productService.findById(itemDto.getProductId());

		PurchaseItemEntity item = purchaseItemRepository.findOne(itemDto.getId());

		product.setStock(product.getStock() - item.getQuantity());

		purchaseItemRepository.save(item);

		stockService.addProductInStock(product, itemDto.getQuantity());

		return item;
	}

	public void deleteItem(Long saleId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		PurchaseEntity purchase = findById(saleId);
		if (purchase == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		PurchaseItemEntity item = purchaseItemRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND);
		}

		if (!item.getSale().getId().equals(purchase.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		purchaseItemRepository.delete(item);
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
