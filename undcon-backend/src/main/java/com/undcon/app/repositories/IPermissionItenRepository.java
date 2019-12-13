package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;

public interface IPermissionItenRepository extends PagingAndSortingRepository<PermissionItenEntity, Long> {

	public List<PermissionItenEntity> findByPermission(PermissionEntity menu);
}
