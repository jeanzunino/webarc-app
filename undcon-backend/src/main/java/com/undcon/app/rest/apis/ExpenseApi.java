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
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.services.ExpenseService;

@Component
@Path("/expenses")
public class ExpenseApi {

	@Autowired
	private ExpenseService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ExpenseEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseEntity get(@PathParam("id") long id) throws UndconException {
		ExpenseEntity customer = service.findById(id);

		return customer;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseEntity post(ExpenseEntity customer) throws UndconException {
		return service.persist(customer);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseEntity put(ExpenseEntity customer) throws UndconException {
		return service.update(customer);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
