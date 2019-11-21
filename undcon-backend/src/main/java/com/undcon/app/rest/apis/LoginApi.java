package com.undcon.app.rest.apis;

import javax.security.auth.login.LoginException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.LoginRequestDto;
import com.undcon.app.dtos.LoginResponseDto;
import com.undcon.app.services.LoginService;

@Component
@Path("/login")
public class LoginApi {

	@Autowired
	private LoginService service;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponseDto post(LoginRequestDto dto) {
		try {
			return service.login(dto);
		} catch (LoginException e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.UNAUTHORIZED);
		} catch (Exception e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}
	} 

}
