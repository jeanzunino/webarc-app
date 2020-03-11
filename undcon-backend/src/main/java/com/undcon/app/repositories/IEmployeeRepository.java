package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.EmployeeEntity;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	public List<EmployeeEntity> findByIdNotAndName(Long id, String name);
}
