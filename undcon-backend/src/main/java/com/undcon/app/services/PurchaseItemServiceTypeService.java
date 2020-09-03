package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.PurchaseItemEntity;
import com.undcon.app.model.PurchaseItemServiceEntity;
import com.undcon.app.model.ServiceTypeEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPurchaseItemServiceRepository;

@Component
public class PurchaseItemServiceTypeService extends AbstractService<PurchaseItemServiceEntity> {

	@Autowired
	private IPurchaseItemServiceRepository purchaseItemServiceRepository;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private UserService userService;

	@Autowired
	private ServiceTypeService serviceTypeService;

	@Transactional
	public PurchaseItemEntity addItemService(long purchaseId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(getResourceType());
		PurchaseEntity purchase = purchaseService.findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		ServiceTypeEntity service = serviceTypeService.findById(itemDto.getItemId());
		if (service == null) {
			throw new UndconException(UndconError.SERVICE_TYPE_NOT_FOUND);
		}

		UserEntity user = userService.getCurrentUser();

		PurchaseItemServiceEntity item = new PurchaseItemServiceEntity(null, service, purchase, user,
				service.getPrice(), itemDto.getQuantity());

		return purchaseItemServiceRepository.save(item);
	}

	@Transactional
	public PurchaseItemEntity updateServiceItem(long purchaseId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(getResourceType());

		PurchaseEntity purchase = purchaseService.findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		UserEntity user = userService.getCurrentUser();
		ServiceTypeEntity service = serviceTypeService.findById(itemDto.getItemId());

		PurchaseItemServiceEntity item = purchaseItemServiceRepository.findOne(itemDto.getId());
		item.setService(service);
		item.setPrice(item.getPrice());
		item.setUser(user);

		return purchaseItemServiceRepository.save(item);
	}

	public void deleteServiceItem(Long purchaseId, long itemId) throws UndconException {
		permissionService.checkPermission(getResourceType());

		PurchaseEntity purchase = purchaseService.findById(purchaseId);
		if (purchase == null) {
			throw new UndconException(UndconError.PURCHASE_NOT_FOUND);
		}

		PurchaseItemServiceEntity item = purchaseItemServiceRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.PURCHASE_ITEM_NOT_FOUND);
		}

		if (!item.getPurchase().getId().equals(purchase.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		purchaseItemServiceRepository.delete(item);
	}

	@Override
	protected JpaRepository<PurchaseItemServiceEntity, Long> getRepository() {
		return purchaseItemServiceRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.PURCHASE;
	}

}
