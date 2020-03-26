package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
	
	public Page<ProductEntity> findAllByName(String name, Pageable pageable);
	
	public List<ProductEntity> findByIdNotAndName(Long id, String name);
}
