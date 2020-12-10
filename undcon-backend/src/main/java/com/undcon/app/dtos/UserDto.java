package com.undcon.app.dtos;

import com.undcon.app.model.PermissionEntity;

public class UserDto {

	private Long id;
	private String login;
	private PersonDto employee;
	private PermissionEntity permission;
	private boolean active;

	public UserDto(Long id, String login, boolean active, PersonDto employee, PermissionEntity permission) {
		super();
		this.id = id;
		this.login = login;
		this.active = active;
		this.employee = employee;
		this.permission = permission;
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

}
