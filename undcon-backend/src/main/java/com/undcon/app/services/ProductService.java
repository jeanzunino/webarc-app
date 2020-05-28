package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.repositories.IProductRepository;
import com.undcon.app.repositories.ProductRepositoryImpl;

@Component
public class ProductService extends AbstractService<ProductEntity> {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private ProductRepositoryImpl productRepositoryImpl;

	public Page<ProductEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(ProductEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(ProductEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(ProductEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProductEntity> findByIdNotAndName = productRepository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public List<ProductEntity> getStockMin() {
		return productRepositoryImpl.getStockMin();
	}

	@Override
	protected JpaRepository<ProductEntity, Long> getRepository() {
		return productRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.PRODUCT;
	}

}
