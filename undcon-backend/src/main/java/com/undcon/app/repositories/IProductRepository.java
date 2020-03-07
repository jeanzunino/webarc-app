package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
	
	public List<ProductEntity> findByIdNotAndName(Long id, String name);
}
