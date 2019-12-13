package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.PermissionEntity;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
	
	public List<PermissionEntity> findByIdNotAndName(Long id, String name);
}
