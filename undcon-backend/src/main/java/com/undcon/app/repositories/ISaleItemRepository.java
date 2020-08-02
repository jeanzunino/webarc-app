package com.undcon.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemServiceEntity;

public interface ISaleItemRepository extends JpaRepository<SaleItemEntity, Long>, QueryDslPredicateExecutor<SaleItemEntity> {
	
	public Page<SaleItemServiceEntity> findAllBySaleId(Long saleId, Pageable pageable);
}
