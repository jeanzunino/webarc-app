package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.repositories.IProductCategoryRepository;

@Component
public class ProductCategoryService extends AbstractService<ProductCategoryEntity> {

	@Autowired
	private IProductCategoryRepository productCategoryRepository;
	
	public Page<ProductCategoryEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(ProductCategoryEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(ProductCategoryEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(ProductCategoryEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(ProductCategoryEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}
	
	private void validateName(Long id, String name) throws UndconException {
		List<ProductCategoryEntity> findByIdNotAndName = productCategoryRepository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	@Override
	protected JpaRepository<ProductCategoryEntity, Long> getRepository() {
		return productCategoryRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.PRODUCT_CATEGORY;
	}

}
