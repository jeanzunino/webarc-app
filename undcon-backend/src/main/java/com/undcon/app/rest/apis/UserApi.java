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

import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IUserRepository;

@Component
@Path("/users")
public class UserApi {

	@Autowired
	private IUserRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserEntity> getAll() {
		// Return the DTO List:
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserEntity get(@PathParam("id") long id) {
		UserEntity customer = repository.findOne(id);

		return customer;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserEntity post(UserEntity customer) {
		return repository.save(customer);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}