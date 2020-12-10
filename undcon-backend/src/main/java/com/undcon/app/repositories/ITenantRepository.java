package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.TenantEntity;

public interface ITenantRepository extends JpaRepository<TenantEntity, Long>, QueryDslPredicateExecutor<TenantEntity> {
	
	public TenantEntity findById(Long id);
	
	public List<TenantEntity> findByIdNotAndNameIgnoreCase(Long id, String name);
	
	public List<TenantEntity> findByIdNotAndSchemaNameIgnoreCase(Long id, String schemaName);
}
