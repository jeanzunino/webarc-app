package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItemEntity;

public interface IPermissionItenRepository extends JpaRepository<PermissionItemEntity, Long> {

	public List<PermissionItemEntity> findByPermission(PermissionEntity menu);
}
