package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	public Page<CustomerEntity> findAllByName(String name, Pageable pageable);
	
	public List<CustomerEntity> findByIdNotAndName(Long id, String name);
}
