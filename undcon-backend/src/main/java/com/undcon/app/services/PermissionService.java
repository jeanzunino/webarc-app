package com.undcon.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItemEntity;
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

	public Page<PermissionEntity> getAll(String name, Integer page, Integer size) {
		if (StringUtils.isEmpty(name)) {
			return permissionRepository.findAll(PageUtils.createPageRequest(page, size));
		}
		return permissionRepository.findAllByNameContainingIgnoreCase(name, PageUtils.createPageRequest(page, size));
	}

	public PermissionEntity findById(Long id) {
		return permissionRepository.findOne(id);
	}
	
	public PermissionItemEntity findItenById(Long id) {
		return permissionItenRepository.findOne(id);
	}
	
	public PermissionEntity validateAndGet(Long id) throws UndconException {
		PermissionEntity permission = findById(id);
		if(permission == null) {
			throw new UndconException(UndconError.PERMISSION_NOT_FOUND);
		}
		return permission;
	}

	public PermissionEntity persist(PermissionEntity entity) throws UndconException {
		checkPermission(ResourceType.Permission);
		validateName(0L, entity.getName());
		return permissionRepository.save(entity);
	}

	public PermissionEntity update(PermissionEntity entity) throws UndconException {
		checkPermission(ResourceType.Permission);
		validateName(entity.getId(), entity.getName());
		return permissionRepository.save(entity);
	}

	public void delete(long id) throws ForbiddenException, UndconException {
		checkPermission(ResourceType.Permission);
		permissionRepository.delete(id);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<PermissionEntity> findByIdNotAndName = permissionRepository.findByIdNotAndName(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	public void checkPermission(ResourceType resource) throws UndconException {
		UserEntity find = userService.getCurrentUser();
		List<ResourceType> resources = getResourcesOfUser(find);
		boolean hasResource = resources.stream().filter(iten -> iten == resource).count() > 0;
		if (!hasResource) {
			throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity(
					new ErrorPermissionModel(resource, "Usuário não possui permissão para o recurso " + resource))
					.build());
		}
	}

	public List<ResourceType> getResourcesOfUser(UserEntity user) {
		List<ResourceType> resources = new ArrayList<ResourceType>();
		List<PermissionItemEntity> itens = permissionItenRepository.findByPermission(user.getPermission());
		itens.stream().forEach(resource -> resources.add(resource.getResourceType()));
		return resources;
	}
}
