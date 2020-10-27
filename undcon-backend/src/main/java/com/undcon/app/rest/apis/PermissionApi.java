package com.undcon.app.rest.apis;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItemEntity;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.services.PermissionService;

/**
 * Api de Permissões
 */
@Component
@Path("/permissions")
public class PermissionApi {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private IPermissionItenRepository repositoryIten;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<PermissionEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return permissionService.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity get(@PathParam("id") long id) throws UndconException {
		PermissionEntity entity = permissionService.findById(id);
		return entity;
	}

	@GET
	@Path("/resources")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResourceType> getResources() {
		//TODO Alterar para retornar as permissões
		return Arrays.asList(ResourceType.values());
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity post(PermissionEntity entity) throws UndconException {
		return permissionService.persist(entity);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity put(PermissionEntity entity) throws UndconException {
		return permissionService.update(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws ForbiddenException, UndconException {
		permissionService.delete(id);
	}

	@POST
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionItemEntity postIten(PermissionItemEntity entity) {
		return repositoryIten.save(entity);
	}

	@GET
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PermissionItemEntity> getItens(@PathParam("id") long id) throws UndconException {
		PermissionEntity permission = permissionService.findById(id);
		List<PermissionItemEntity> items = repositoryIten.findByPermission(permission);
		return items;
	}

	@DELETE
	@Path("/{id}/itens/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void postIten(@PathParam("itemId") long itemId) {
		PermissionItemEntity findItenById = permissionService.findItenById(itemId);
		repositoryIten.delete(findItenById);
	}

}
