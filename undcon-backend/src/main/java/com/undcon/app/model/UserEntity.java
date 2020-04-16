package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "login")
	private String login;

	@Column(name = "senha")
	private String password;

	@Column(name = "ativo", nullable = false)
	private boolean active;

	@Column(name = "resetar_senha", nullable = false)
	private boolean resetPassword;

	@OneToOne
	@JoinColumn(name = "empregado_id", nullable = true)
	private EmployeeEntity employee;

	@OneToOne
	@JoinColumn(name = "permissao_id", nullable = true)
	private PermissionEntity permission;

	protected UserEntity() {
	}

	public UserEntity(Long id, String login, String password, EmployeeEntity employee, PermissionEntity permission,
			boolean active, boolean resetPassword) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.employee = employee;
		this.permission = permission;
		this.active = active;
		this.resetPassword = resetPassword;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PermissionEntity getPermission() {
		return permission;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public void setPermission(PermissionEntity permission) {
		this.permission = permission;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	public boolean isResetPassword() {
		return resetPassword;
	}

}
