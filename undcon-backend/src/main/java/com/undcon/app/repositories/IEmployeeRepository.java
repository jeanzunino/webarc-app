package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.EmployeeEntity;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
