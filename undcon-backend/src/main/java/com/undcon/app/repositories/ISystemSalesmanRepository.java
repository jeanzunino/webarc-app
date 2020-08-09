package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.SystemSalesmanEntity;

public interface ISystemSalesmanRepository extends JpaRepository<SystemSalesmanEntity, Long>, QueryDslPredicateExecutor<SystemSalesmanEntity> {

	public List<SystemSalesmanEntity> findByIdNotAndNameIgnoreCase(Long id, String name);
}
