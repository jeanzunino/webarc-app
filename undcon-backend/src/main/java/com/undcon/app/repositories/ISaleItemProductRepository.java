package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemProductEntity;

public interface ISaleItemProductRepository
		extends JpaRepository<SaleItemProductEntity, Long>, QueryDslPredicateExecutor<SaleItemEntity> {
	
	public List<SaleItemProductEntity> findAllById(Long id, Pageable pageable);
}