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
import com.undcon.app.model.CostCenterEntity;
import com.undcon.app.services.CostCenterService;

/**
 * Api de Centros de Custos
 */
@Component
@Path("/costsCenters")
public class CostCenterApi {

	@Autowired
	private CostCenterService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<CostCenterEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CostCenterEntity get(@PathParam("id") long id) throws UndconException {
		CostCenterEntity entity = service.findById(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public CostCenterEntity post(CostCenterEntity entity) throws UndconException {
		return service.persist(entity);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CostCenterEntity put(CostCenterEntity entity) throws UndconException {
		return service.update(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
