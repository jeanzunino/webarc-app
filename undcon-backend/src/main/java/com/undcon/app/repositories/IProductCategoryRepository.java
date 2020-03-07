package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.model.ProductEntity;

public interface IProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {
	
	public List<ProductCategoryEntity> findByIdNotAndName(Long id, String name);
}
