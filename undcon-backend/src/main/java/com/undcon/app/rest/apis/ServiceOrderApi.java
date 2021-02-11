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
import com.undcon.app.model.ServiceOrderEntity;
import com.undcon.app.services.ServiceOrderService;

/**
 * Api de Tipos de Serviços
 */
@Component
@Path("/servicesOrders")
public class ServiceOrderApi {

	@Autowired
	private ServiceOrderService serviceOrderService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ServiceOrderEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return serviceOrderService.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceOrderEntity get(@PathParam("id") long id) throws UndconException {
		ServiceOrderEntity entity = serviceOrderService.findById(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceOrderEntity post(ServiceOrderEntity service) throws UndconException {
		return serviceOrderService.persist(service);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceOrderEntity put(ServiceOrderEntity service) throws UndconException {
		return serviceOrderService.update(service);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		serviceOrderService.delete(id);
	}
}
