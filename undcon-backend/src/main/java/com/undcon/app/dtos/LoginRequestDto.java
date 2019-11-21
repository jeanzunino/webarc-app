package com.undcon.app.dtos;

public class LoginRequestDto {

	private String login;
	private String password;
	
	public LoginRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public LoginRequestDto(String login, String pass) {
		super();
		this.login = login;
		this.password = pass;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	
}
