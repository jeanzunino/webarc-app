package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemServiceEntity;

public interface ISaleItemServiceRepository
		extends JpaRepository<SaleItemServiceEntity, Long>, QueryDslPredicateExecutor<SaleItemEntity> {

	public List<SaleItemServiceEntity> findAllBySaleId(Long saleId, Pageable pageable);
}