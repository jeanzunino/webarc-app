package com.undcon.app.rest.apis;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IUserRepository;
import com.undcon.app.services.UserService;

@Component
@Path("/users")
public class UserApi {

	@Autowired
	private IUserRepository repository;
	
	@Autowired
	private UserService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserEntity> getAll() {
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
		try {
			return service.save(customer);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}
