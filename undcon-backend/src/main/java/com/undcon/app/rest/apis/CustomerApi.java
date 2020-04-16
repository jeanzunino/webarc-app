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
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.services.CustomerService;

@Component
@Path("/customers")
public class CustomerApi {

	@Autowired
	private CustomerService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<CustomerEntity> getAll(@QueryParam("name") String name, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(name, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerEntity get(@PathParam("id") long id) {
		CustomerEntity customer = service.findById(id);
		return customer;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerEntity post(CustomerEntity customer) throws UndconException {
		return service.persist(customer);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerEntity put(CustomerEntity customer) throws UndconException {
		return service.update(customer);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
