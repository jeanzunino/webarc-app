package com.undcon.app.dtos;

import com.undcon.app.model.PermissionEntity;

public class UserDto {

	private Long id;
	private String login;
	private PersonDto employee;
	private PermissionEntity permission;
	private boolean active;
	private String tokenResetarSenha;

	public UserDto(Long id, String login, boolean active, PersonDto employee, PermissionEntity permission, String tokenResetarSenha) {
		super();
		this.id = id;
		this.login = login;
		this.active = active;
		this.employee = employee;
		this.permission = permission;
		this.tokenResetarSenha = tokenResetarSenha;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public PersonDto getEmployee() {
		return employee;
	}

	public PermissionEntity getPermission() {
		return permission;
	}
	
	public boolean isActive() {
		return active;
	}

	public String getTokenResetarSenha() {
		return tokenResetarSenha;
	}

	public void setTokenResetarSenha(String tokenResetarSenha) {
		this.tokenResetarSenha = tokenResetarSenha;
	}

}
