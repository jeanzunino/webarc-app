package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.CustomerEntity;

public interface ICustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {
}
