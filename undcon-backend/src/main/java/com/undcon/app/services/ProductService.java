package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.repositories.IProductRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class ProductService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private PermissionService permissionService;

	public List<ProductEntity> getAll(String name, Integer page, Integer size) {
		if (StringUtils.isEmpty(name)) {
			return productRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
		}
		return productRepository.findAllByName(name, PageUtils.createPageRequest(page, size)).getContent();
	}

	public ProductEntity findById(Long id) {
		return productRepository.findOne(id);
	}

	public ProductEntity persist(ProductEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PRODUCT);
		validateName(0L, entity.getName());
		return productRepository.save(entity);
	}

	public ProductEntity update(ProductEntity entity) throws UndconException {
		permissionService.checkPermission(ResourseType.PRODUCT);
		validateName(entity.getId(), entity.getName());
		return productRepository.save(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProductEntity> findByIdNotAndName = productRepository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void delete(long id) {
		permissionService.checkPermission(ResourseType.PRODUCT);
		productRepository.delete(id);
	}
}
