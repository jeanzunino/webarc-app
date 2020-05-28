package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.ProductCategoryEntity;

public interface IProductCategoryRepository
		extends JpaRepository<ProductCategoryEntity, Long>, QueryDslPredicateExecutor<ProductCategoryEntity> {

	public Page<ProductCategoryEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

	public List<ProductCategoryEntity> findByIdNotAndName(Long id, String name);
}
