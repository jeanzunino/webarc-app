package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;

public interface IPermissionItenRepository extends JpaRepository<PermissionItenEntity, Long> {

	public List<PermissionItenEntity> findByPermission(PermissionEntity menu);
}
