package com.undcon.app.services;

import java.util.List;

import javax.ws.rs.ForbiddenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.repositories.IPermissionRepository;
import com.undcon.app.repositories.IUserRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class PermissionService {

	@Autowired
	private IPermissionRepository permissionRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IPermissionItenRepository permissionItenRepository;

	public List<PermissionEntity> getAll(Integer page, Integer size) {
		return permissionRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
	}

	public PermissionEntity findById(Long id) {
		return permissionRepository.findOne(id);
	}

	public PermissionEntity persist(PermissionEntity entity) throws UndconException {
		checkPermission(ResourseType.Permission);
		validateName(0L, entity.getName());
		return permissionRepository.save(entity);
	}

	public PermissionEntity update(PermissionEntity entity) throws UndconException {
		checkPermission(ResourseType.Permission);
		validateName(entity.getId(), entity.getName());
		return permissionRepository.save(entity);
	}

	public void delete(long id) {
		checkPermission(ResourseType.Permission);
		permissionRepository.delete(id);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<PermissionEntity> findByIdNotAndName = permissionRepository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void checkPermission(ResourseType configuration) throws ForbiddenException {
		Long currentUserId = ThreadLocalStorage.getUser();
		UserEntity find = userRepository.findOne(currentUserId);
		PermissionEntity permission = find.getPermission();
		verifyPermissionHasResource(permission, configuration);
	}

	private void verifyPermissionHasResource(PermissionEntity permission, ResourseType configuration)
			throws ForbiddenException {
		List<PermissionItenEntity> itens = permissionItenRepository.findByPermission(permission);
		boolean hasResource = itens.stream().filter(iten -> iten.getResourceType() == configuration).count() > 0;
		if (!hasResource) {
			throw new ForbiddenException("Usuário não possui permissão para o recurso " + configuration);
		}
	}
}
