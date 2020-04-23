package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.repositories.IProductCategoryRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class ProductCategoryService {

	@Autowired
	private IProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<ProductCategoryEntity> getAll(String name, Integer page, Integer size) {
		if(StringUtils.isEmpty(name)) {
			return productCategoryRepository.findAll(PageUtils.createPageRequest(page, size));
		}
        return productCategoryRepository.findAllByNameContainingIgnoreCase(name, PageUtils.createPageRequest(page, size));
    }
	
	public ProductCategoryEntity findById(Long id) {
        return productCategoryRepository.findOne(id);
    }
	
	public ProductCategoryEntity persist(ProductCategoryEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.PRODUCT_CATEGORY);
		validateName(0L, entity.getName());
		return productCategoryRepository.save(entity);
	}

	public ProductCategoryEntity update(ProductCategoryEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.PRODUCT_CATEGORY);
		validateName(entity.getId(), entity.getName());
		return productCategoryRepository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProductCategoryEntity> findByIdNotAndName = productCategoryRepository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.PRODUCT_CATEGORY);
		productCategoryRepository.delete(id);
	}
}
