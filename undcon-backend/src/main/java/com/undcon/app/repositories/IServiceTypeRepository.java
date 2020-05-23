package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.ServiceTypeEntity;

public interface IServiceTypeRepository
		extends JpaRepository<ServiceTypeEntity, Long>, QueryDslPredicateExecutor<ServiceTypeEntity> {

	public List<ServiceTypeEntity> findByIdNotAndName(Long id, String name);
}
