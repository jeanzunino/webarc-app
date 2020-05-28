package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.EmployeeEntity;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long>, QueryDslPredicateExecutor<EmployeeEntity> {
	
	public Page<EmployeeEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
	
	public List<EmployeeEntity> findByIdNotAndNameIgnoreCase(Long id, String name);
}
