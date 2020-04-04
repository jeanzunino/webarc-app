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
import com.undcon.app.model.ServiceTypeEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.ServiceTypeService;

@Component
@Path("/serviceTypes")
public class ServiceTypeApi {

	@Autowired
	private ServiceTypeService serviceTypeService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ServiceTypeEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return serviceTypeService.getAll(page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceTypeEntity get(@PathParam("id") long id) {
		ServiceTypeEntity entity = serviceTypeService.findById(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceTypeEntity post(ServiceTypeEntity service) {
		try {
			return serviceTypeService.persist(service);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceTypeEntity put(ServiceTypeEntity service) {
		try {
			return serviceTypeService.update(service);
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
			serviceTypeService.delete(id);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}
}
