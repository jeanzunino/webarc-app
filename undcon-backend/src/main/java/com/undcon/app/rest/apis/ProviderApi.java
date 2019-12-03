package com.undcon.app.rest.apis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.ProviderEntity;
import com.undcon.app.repositories.IProviderRepository;

@Component
@Path("/providers")
public class ProviderApi {

	@Autowired
	private IProviderRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProviderEntity> getAll() {
		// Return the DTO List:
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProviderEntity get(@PathParam("id") long id) {
		ProviderEntity Provider = repository.findOne(id);

		return Provider;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProviderEntity post(ProviderEntity Provider) {
		return repository.save(Provider);
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProviderEntity put(ProviderEntity provider) {
		return repository.save(provider);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}