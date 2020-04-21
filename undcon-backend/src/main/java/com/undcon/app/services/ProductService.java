package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.repositories.IProductRepository;
import com.undcon.app.repositories.ProductRepositoryImpl;
import com.undcon.app.utils.PageUtils;

@Component
public class ProductService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private ProductRepositoryImpl productRepositoryImpl;
	
	@Autowired
	private PermissionService permissionService;

	public Page<ProductEntity> getAll(String name, Integer page, Integer size) {
		if (StringUtils.isEmpty(name)) {
			return productRepository.findAll(PageUtils.createPageRequest(page, size));
		}
		return productRepository.findAllByNameContainingIgnoreCase(name, PageUtils.createPageRequest(page, size));
	}

	public ProductEntity findById(Long id) {
		return productRepository.findOne(id);
	}

	public ProductEntity persist(ProductEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.PRODUCT);
		validateName(0L, entity.getName());
		return productRepository.save(entity);
	}

	public ProductEntity update(ProductEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.PRODUCT);
		validateName(entity.getId(), entity.getName());
		return productRepository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProductEntity> findByIdNotAndName = productRepository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.PRODUCT);
		productRepository.delete(id);
	}

	public List<ProductEntity> getStockMin() {
		return productRepositoryImpl.getStockMin();
	}
	
}
