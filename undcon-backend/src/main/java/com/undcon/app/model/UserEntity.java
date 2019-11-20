package com.undcon.app.model;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "login")
	private String login;

	@OneToOne
	@JoinColumn(name = "empregado_id", nullable = true)
	private EmployeeEntity employee;

	protected UserEntity() {
	}

	public UserEntity(Long id, String login, EmployeeEntity employee) {
		super();
		this.id = id;
		this.login = login;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	
}