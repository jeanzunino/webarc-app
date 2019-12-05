package com.undcon.app.model;

import javax.persistence.*;

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

	@OneToOne
	@JoinColumn(name = "empregado_id", nullable = true)
	private EmployeeEntity employee;

	protected UserEntity() {
	}

	public UserEntity(Long id, String login,String password, EmployeeEntity employee) {
		super();
		this.id = id;
		this.login = login;
		this.employee = employee;
		this.password = password;
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
	
}