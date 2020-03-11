package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
	public List<CustomerEntity> findByIdNotAndName(Long id, String name);
}
