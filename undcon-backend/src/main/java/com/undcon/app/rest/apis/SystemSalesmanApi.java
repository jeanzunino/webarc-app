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
import com.undcon.app.model.SystemSalesmanEntity;
import com.undcon.app.services.SystemSalesmanService;

/**
 * Api de Empregados/Funcionários
 */
@Component
@Path("/system/salesmans")
public class SystemSalesmanApi {

	@Autowired
	private SystemSalesmanService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<SystemSalesmanEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SystemSalesmanEntity get(@PathParam("id") long id) throws UndconException {
		return service.findById(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public SystemSalesmanEntity post(SystemSalesmanEntity entity) throws UndconException {
		return service.persist(entity);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SystemSalesmanEntity put(SystemSalesmanEntity entity) throws UndconException {
		return service.update(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
