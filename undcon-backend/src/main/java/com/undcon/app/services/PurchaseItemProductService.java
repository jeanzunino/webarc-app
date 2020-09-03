package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.PurchaseItemEntity;
import com.undcon.app.model.PurchaseItemProductEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPurchaseItemProductRepository;

@Component
public class PurchaseItemProductService extends AbstractService<PurchaseItemProductEntity> {

	@Autowired
	private IPurchaseItemProductRepository purchaseItemProductRepository;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private StockService stockService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Transactional
	public PurchaseItemEntity addItemProduct(long purchaseId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);
		PurchaseEntity purchase = purchaseService.findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		ProductEntity product = productService.findById(itemDto.getItemId());

		if (product == null) {
			throw new UndconException(UndconError.PRODUCT_NOT_FOUND);
		}
		stockService.checkStockAvaiable(product, itemDto.getQuantity());

		UserEntity user = userService.getCurrentUser();

		PurchaseItemProductEntity item = new PurchaseItemProductEntity(null, product, purchase, user,
				product.getSalePrice(), itemDto.getQuantity());
		item = purchaseItemProductRepository.save(item);

		stockService.addProductInStock(product, itemDto.getQuantity());
		return item;
	}

	@Transactional
	public PurchaseItemEntity updateProductItem(long purchaseId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.PURCHASE);

		PurchaseEntity purchase = purchaseService.findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		ProductEntity product = productService.findById(itemDto.getItemId());

		PurchaseItemProductEntity item = purchaseItemProductRepository.findOne(itemDto.getId());

		product.setStock(product.getStock() - item.getQuantity());

		purchaseItemProductRepository.save(item);

		stockService.addProductInStock(product, itemDto.getQuantity());

		return item;
	}

	@Transactional
	public void deleteProductItem(Long purchaseId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		PurchaseEntity purchase = purchaseService.findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		PurchaseItemProductEntity item = purchaseItemProductRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.PURCHASE_ITEM_NOT_FOUND);
		}

		if (!item.getPurchase().getId().equals(purchase.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		purchaseItemProductRepository.delete(item);
	}

	@Override
	protected JpaRepository<PurchaseItemProductEntity, Long> getRepository() {
		return purchaseItemProductRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SALE;
	}

}
