package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
