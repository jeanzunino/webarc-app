package com.undcon.app.rest.apis;

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

import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.TenantEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.TenantService;

@Component
@Path("/tenants")
public class TenantApi {

	@Autowired
	private TenantService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TenantEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		try {
			return service.getAll(page, size);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity get(@PathParam("id") long id) {
		try {
			return service.findById(id);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity post(TenantEntity tenant) {
		try {
			return service.persist(tenant);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity put(TenantEntity tenant) {
		try {
			return service.update(tenant);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		try {
			service.delete(id);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}
}
