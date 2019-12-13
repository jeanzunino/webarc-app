package com.undcon.app.rest.apis;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.repositories.IPermissionRepository;
import com.undcon.app.services.PermissionService;

@Component
@Path("/permissions")
public class PermissionApi {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private IPermissionRepository repository;

	@Autowired
	private IPermissionItenRepository repositoryIten;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PermissionEntity> getAll() {
		return permissionService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity get(@PathParam("id") long id) {
		PermissionEntity entity = repository.findOne(id);
		return entity;
	}

	@GET
	@Path("/resources")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResourseType> getResources() {
		return Arrays.asList(ResourseType.values());
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity post(PermissionEntity entity) {
		return permissionService.persist(entity);
	}

	@POST
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionItenEntity postIten(PermissionItenEntity entity) {
		return repositoryIten.save(entity);
	}

	@GET
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PermissionItenEntity> getItens(@PathParam("id") long id) {
		PermissionEntity permission = repository.findOne(id);
		List<PermissionItenEntity> items = repositoryIten.findByPermission(permission);
		return items;
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity put(PermissionEntity entity) {
		return permissionService.update(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}
