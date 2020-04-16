package com.undcon.app.rest.apis;

import javax.ws.rs.DELETE;
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

import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.TenantEntity;
import com.undcon.app.services.TenantService;

@Component
@Path("/tenants")
public class TenantApi {

	@Autowired
	private TenantService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<TenantEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size)
			throws UndconException {
		return service.getAll(page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity get(@PathParam("id") long id) throws UndconException {
		return service.findById(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity post(TenantEntity tenant) throws UndconException {
		return service.persist(tenant);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity put(TenantEntity tenant) throws UndconException {
		return service.update(tenant);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
