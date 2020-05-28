package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.SaleItemEntity;

public interface ISaleItemRepository extends JpaRepository<SaleItemEntity, Long>, QueryDslPredicateExecutor<SaleItemEntity> {
	
}
