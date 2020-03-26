package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourseType;
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

	public List<ProductCategoryEntity> getAll(String name, Integer page, Integer size) {
		if(StringUtils.isEmpty(name)) {
			return productCategoryRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
		}
        return productCategoryRepository.findAllByName(name, PageUtils.createPageRequest(page, size)).getContent();
    }
	
	public ProductCategoryEntity findById(Long id) {
        return productCategoryRepository.findOne(id);
    }
	
	public ProductCategoryEntity persist(ProductCategoryEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PRODUCT_CATEGORY);
		validateName(0L, entity.getName());
		return productCategoryRepository.save(entity);
	}

	public ProductCategoryEntity update(ProductCategoryEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PRODUCT_CATEGORY);
		validateName(entity.getId(), entity.getName());
		return productCategoryRepository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProductCategoryEntity> findByIdNotAndName = productCategoryRepository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) {
		permissionService.checkPermission(ResourseType.PRODUCT_CATEGORY);
		productCategoryRepository.delete(id);
	}
}
