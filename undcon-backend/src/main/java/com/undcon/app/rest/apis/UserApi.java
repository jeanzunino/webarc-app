package com.undcon.app.rest.apis;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.UserDto;
import com.undcon.app.mappers.UserMapper;
import com.undcon.app.model.UserEntity;
import com.undcon.app.services.UserService;

@Component
@Path("/users")
public class UserApi {

	@Autowired
    private UserMapper mapper;

	@Autowired
	private UserService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDto> getAll() {
		List<UserEntity> findAll = service.getAll();
		return mapper.toDto(findAll);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto get(@PathParam("id") long id) {
		UserEntity customer = service.findById(id);
		return mapper.toDto(customer);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto post(UserEntity user) {
		try {
			return  mapper.toDto(service.persist(user));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto put(UserEntity user) {
		try {
			return mapper.toDto(service.update(user));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
	    service.delete(id);
	}
	
	@PUT
    @Path("/{id}/resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public void resetPassword(@PathParam("id") long id) {
        try {
            service.resetPassword(id);
        } catch (IllegalAccessException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.FORBIDDEN);
        }
    }
}
