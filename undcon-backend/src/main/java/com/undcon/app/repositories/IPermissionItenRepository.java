package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItemEntity;

public interface IPermissionItenRepository extends JpaRepository<PermissionItemEntity, Long>, QueryDslPredicateExecutor<PermissionItemEntity> {

	public List<PermissionItemEntity> findByPermission(PermissionEntity menu);
	
	public List<PermissionItemEntity> findByPermissionAndResourceType(PermissionEntity permission, ResourceType resourceType);
}
