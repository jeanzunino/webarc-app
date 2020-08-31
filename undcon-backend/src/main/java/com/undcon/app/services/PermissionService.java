package com.undcon.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItemEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.repositories.IPermissionRepository;
import com.undcon.app.rest.models.ErrorPermissionModel;

@Component
public class PermissionService extends AbstractService<PermissionEntity>{

	@Autowired
	private IPermissionRepository permissionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private IPermissionItenRepository permissionItenRepository;

	public Page<PermissionEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(PermissionEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(PermissionEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(PermissionEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(PermissionEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
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

	@Override
	protected JpaRepository<PermissionEntity, Long> getRepository() {
		return permissionRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.PERMISSION;
	}
}
