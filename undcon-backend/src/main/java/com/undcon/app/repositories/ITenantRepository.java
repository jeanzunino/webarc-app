package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.TenantEntity;

public interface ITenantRepository extends JpaRepository<TenantEntity, Long>, QueryDslPredicateExecutor<TenantEntity> {
	public TenantEntity findById(Long id);
}
