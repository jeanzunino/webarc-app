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
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.PurchaseService;

@Component
@Path("/purchases")
public class PurchaseApi {

	@Autowired
	private PurchaseService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PurchaseEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return service.getAll(page, size);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity get(@PathParam("id") long id) {
		PurchaseEntity Provider = service.findById(id);
		return Provider;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity post(PurchaseEntity Provider) {
		try {
			return service.persist(Provider);
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity put(PurchaseEntity provider) {
		try {
			return service.update(provider);
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		try {
			service.delete(id);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}
}
