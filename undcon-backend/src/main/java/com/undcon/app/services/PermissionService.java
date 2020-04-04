package com.undcon.app.services;

import java.util.List;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.repositories.IPermissionRepository;
import com.undcon.app.rest.models.ErrorPermissionModel;
import com.undcon.app.utils.PageUtils;

@Component
public class PermissionService {

	@Autowired
	private IPermissionRepository permissionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private IPermissionItenRepository permissionItenRepository;

	public List<PermissionEntity> getAll(String name, Integer page, Integer size) {
		if (StringUtils.isEmpty(name)) {
			return permissionRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
		}
		return permissionRepository.findAllByName(name, PageUtils.createPageRequest(page, size)).getContent();
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

	public void delete(long id) throws ForbiddenException, UndconException {
		checkPermission(ResourseType.Permission);
		permissionRepository.delete(id);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<PermissionEntity> findByIdNotAndName = permissionRepository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void checkPermission(ResourseType configuration) throws UndconException {
		UserEntity find = userService.getCurrentUser();
		PermissionEntity permission = find.getPermission();
		verifyPermissionHasResource(permission, configuration);
	}

	private void verifyPermissionHasResource(PermissionEntity permission, ResourseType resource)
			throws ForbiddenException {
		List<PermissionItenEntity> itens = permissionItenRepository.findByPermission(permission);
		boolean hasResource = itens.stream().filter(iten -> iten.getResourceType() == resource).count() > 0;
		if (!hasResource) {
			throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity(
					new ErrorPermissionModel(resource, "Usuário não possui permissão para o recurso " + resource))
					.build());
		}
	}
}
