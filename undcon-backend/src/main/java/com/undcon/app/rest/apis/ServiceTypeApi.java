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
import com.undcon.app.model.ServiceTypeEntity;
import com.undcon.app.services.ServiceTypeService;

@Component
@Path("/serviceTypes")
public class ServiceTypeApi {

	@Autowired
	private ServiceTypeService serviceTypeService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ServiceTypeEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
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
	public ServiceTypeEntity post(ServiceTypeEntity service) throws UndconException {
		return serviceTypeService.persist(service);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceTypeEntity put(ServiceTypeEntity service) throws UndconException {
		return serviceTypeService.update(service);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		serviceTypeService.delete(id);
	}
}
