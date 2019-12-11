package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.PermissionEntity;

public interface IPermissionRepository extends PagingAndSortingRepository<PermissionEntity, Long> {
	
	public List<PermissionEntity> findByIdNotAndName(Long id, String name);
}
