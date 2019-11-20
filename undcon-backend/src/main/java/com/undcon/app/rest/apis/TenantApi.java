package com.undcon.app.rest.apis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.TenantEntity;
import com.undcon.app.repositories.ITenantRepository;

@Component
@Path("/tenants")
public class TenantApi {

	@Autowired
	private ITenantRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TenantEntity> getAll() {
		// Return the DTO List:
		return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity get(@PathParam("id") long id) {
		return repository.findOne(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TenantEntity post(TenantEntity tenant) {
		return repository.save(tenant);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}
