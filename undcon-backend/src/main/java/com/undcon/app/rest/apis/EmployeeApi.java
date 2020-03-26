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
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.EmployeeService;

@Component
@Path("/employees")
public class EmployeeApi {

	@Autowired
	private EmployeeService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmployeeEntity> getAll(@QueryParam("name") String name, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return service.getAll(name, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeEntity get(@PathParam("id") long id) {
		EmployeeEntity customer = service.findById(id);

		return customer;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeEntity post(EmployeeEntity customer) {
		try {
			return service.persist(customer);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeEntity put(EmployeeEntity customer) {
		try {
			return service.update(customer);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		service.delete(id);
	}
}
