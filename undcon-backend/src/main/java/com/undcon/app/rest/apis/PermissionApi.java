package com.undcon.app.rest.apis;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.PermissionService;

@Component
@Path("/permissions")
public class PermissionApi {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private IPermissionItenRepository repositoryIten;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PermissionEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return permissionService.getAll(page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity get(@PathParam("id") long id) {
		PermissionEntity entity = permissionService.findById(id);
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
		try {
			return permissionService.persist(entity);
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
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
		PermissionEntity permission = permissionService.findById(id);
		List<PermissionItenEntity> items = repositoryIten.findByPermission(permission);
		return items;
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PermissionEntity put(PermissionEntity entity) {
		try {
			return permissionService.update(entity);
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		permissionService.delete(id);
	}
}
