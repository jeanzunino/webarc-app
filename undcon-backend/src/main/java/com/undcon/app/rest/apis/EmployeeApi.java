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
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.services.EmployeeService;

/**
 * Api de Empregados/Funcionários
 */
@Component
@Path("/employees")
public class EmployeeApi {

	@Autowired
	private EmployeeService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<EmployeeEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeEntity get(@PathParam("id") long id) throws UndconException {
		EmployeeEntity customer = service.findById(id);
		return customer;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeEntity post(EmployeeEntity customer) throws UndconException {
		return service.persist(customer);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeEntity put(EmployeeEntity customer) throws UndconException {
		return service.update(customer);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
