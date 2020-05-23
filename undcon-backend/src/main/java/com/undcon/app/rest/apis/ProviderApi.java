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
import com.undcon.app.model.ProviderEntity;
import com.undcon.app.services.ProviderService;

@Component
@Path("/providers")
public class ProviderApi {

	@Autowired
	private ProviderService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ProviderEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProviderEntity get(@PathParam("id") long id) throws UndconException {
		ProviderEntity Provider = service.findById(id);
		return Provider;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProviderEntity post(ProviderEntity Provider) throws UndconException {
		return service.persist(Provider);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProviderEntity put(ProviderEntity provider) throws UndconException {
		return service.update(provider);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
