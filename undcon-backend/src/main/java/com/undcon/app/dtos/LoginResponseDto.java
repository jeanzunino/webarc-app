package com.undcon.app.dtos;

public class LoginResponseDto {

	private String tenant;
	private String token;
	private UserDto user;

	public LoginResponseDto() {
	}

	public LoginResponseDto(String tenant, String token, UserDto user) {
		super();
		this.tenant = tenant;
		this.token = token;
		this.user = user;
	}

	public String getTenant() {
		return tenant;
	}

	public String getToken() {
		return token;
	}

	public UserDto getUser() {
		return user;
	}

}
