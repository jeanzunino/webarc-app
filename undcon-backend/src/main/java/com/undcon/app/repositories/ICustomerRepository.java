package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.CustomerEntity;

public interface ICustomerRepository
		extends JpaRepository<CustomerEntity, Long>, QueryDslPredicateExecutor<CustomerEntity> {

	public Page<CustomerEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

	public List<CustomerEntity> findByIdNotAndNameIgnoreCase(Long id, String name);
}
