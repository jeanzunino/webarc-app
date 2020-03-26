package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ServiceTypeEntity;

public interface IServiceTypeRepository extends JpaRepository<ServiceTypeEntity, Long> {
	
	public List<ServiceTypeEntity> findByIdNotAndName(Long id, String name);
}
