package com.undcon.app.dtos;

public class UserDto {

	private String login;
	private String name;

	public UserDto(String login, String name) {
		super();
		this.login = login;
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

}
