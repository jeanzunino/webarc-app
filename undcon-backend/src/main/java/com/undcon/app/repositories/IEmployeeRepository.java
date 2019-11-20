package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.EmployeeEntity;

public interface IEmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
}
