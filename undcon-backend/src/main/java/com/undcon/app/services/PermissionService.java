package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.PermissionEntity;
import com.undcon.app.repositories.IPermissionRepository;

@Component
public class PermissionService {

	@Autowired
	private IPermissionRepository permissionRepository;

	public PermissionEntity persist(PermissionEntity entity) {
		validateName(0L, entity.getName());
		return permissionRepository.save(entity);
	}

	public PermissionEntity update(PermissionEntity entity) {
		validateName(entity.getId(), entity.getName());
		return permissionRepository.save(entity);
	}

	private void validateName(Long id, String name) {
		List<PermissionEntity> findByIdNotAndName = permissionRepository.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new IllegalArgumentException("Nome já está em uso");
		}
	}
}
