package com.undcon.app.rest.apis;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.LoginRequestDto;
import com.undcon.app.dtos.LoginResponseDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.services.LoginService;

/**
 * Api de Login
 */
@Component
@Path("/login")
public class LoginApi {

	@Autowired
	private LoginService service;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponseDto post(LoginRequestDto dto) throws UndconException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return service.login(dto);
	}

}
