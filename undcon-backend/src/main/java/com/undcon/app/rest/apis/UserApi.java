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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.undcon.app.dtos.ResetPasswordDto;
import com.undcon.app.dtos.UserDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.UserMapper;
import com.undcon.app.model.UserEntity;
import com.undcon.app.services.UserService;
import com.undcon.app.utils.PageUtils;

/**
 * Api de Usuários
 */
@Component
@Path("/users")
public class UserApi {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private UserService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<UserDto> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) throws UndconException {
		Page<UserEntity> findAll = service.getAll(filter, page, size);
		List<UserDto> content = mapper.toDto(findAll.getContent());
		Page<UserDto> pageDto = new PageImpl<UserDto>(content, PageUtils.createPageRequest(page, size), findAll.getTotalElements());
		return pageDto;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto get(@PathParam("id") long id) throws UndconException {
		UserEntity customer = service.findById(id);
		return mapper.toDto(customer);
	}

	@GET
	@Path("/current/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResourceType> getPermissionOfLoggeduser() {
		return service.getPermissionOfLoggeduser();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto post(UserEntity user) throws UndconException, NoSuchAlgorithmException, UnsupportedEncodingException {
			Assert.notNull(user.getEmployee(), "employee is required");
			Assert.notNull(user.getEmployee().getId(), "employee.id is required");
			Assert.notNull(user.getPermission(), "permission is required");
			Assert.notNull(user.getPermission().getId(), "permission.id is required");
			Assert.notNull(user.getPassword(), "password is required");
			return mapper.toDto(service.persist(user));
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto put(UserEntity user) throws UndconException, NoSuchAlgorithmException, UnsupportedEncodingException {
		user.setTokenResetarSenha(service.criptyPassword(user.getTokenResetarSenha()));
		return mapper.toDto(service.update(user));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}

	@PUT
	@Path("/resetPassword")
	@Produces(MediaType.APPLICATION_JSON)
	public void resetPassword(ResetPasswordDto dto) throws UndconException, NoSuchAlgorithmException, UnsupportedEncodingException {
		service.resetPassword(dto);
	}
	
	@PUT
	@Path("/{id}/generateTokenPassword")
	@Produces(MediaType.APPLICATION_JSON)
	public String generateTokenPassword(@PathParam("id") long id) throws UndconException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return service.generateTokenPassword(id);
	}
	
}
