package com.undcon.app.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.enums.SaleStatus;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.repositories.IPurchaseRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class PurchaseService {

	@Autowired
	private IPurchaseRepository purchaseRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserService userService;

	public List<PurchaseEntity> getAll(Integer page, Integer size) {
        return purchaseRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
    }
	
	public PurchaseEntity findById(Long id) {
        return purchaseRepository.findOne(id);
    }
	
	public PurchaseEntity persist(PurchaseEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PURCHASE);
		entity.setStatus(SaleStatus.CREATED);
		entity.setPurchaseDate(new Date(System.currentTimeMillis()));
		entity.setBilled(false);
		entity.setUser(userService.getCurrentUser());
		validateProvider(entity);
		
		return purchaseRepository.save(entity);
	}

	private void validateProvider(PurchaseEntity entity) throws UndconException {
		if(entity.getProvider() == null) {
			throw new UndconException(UndconError.PURCHASE_ENTITY_INVALID_PROVIDER);
		}
	}
	
	public PurchaseEntity update(PurchaseEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PURCHASE);
		
		validateProvider(entity);
		
		return purchaseRepository.save(entity);
	}

	public void delete(long id) {
		permissionService.checkPermission(ResourseType.PURCHASE);
		PurchaseEntity sale = findById(id);
		sale.setStatus(SaleStatus.CANCELED);
		purchaseRepository.save(sale);
	}
}
