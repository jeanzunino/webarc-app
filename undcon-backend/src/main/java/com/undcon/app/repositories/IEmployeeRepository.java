package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.EmployeeEntity;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	public Page<EmployeeEntity> findAllByName(String name, Pageable pageable);
	
	public List<EmployeeEntity> findByIdNotAndName(Long id, String name);
}
