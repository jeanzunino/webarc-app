package com.undcon.app.dtos;

public class OpenPdvDto {

	private Long id;
	private String login;
	private String password;
	private String pdvValue;

	public OpenPdvDto() {
		// TODO Auto-generated constructor stub
	}

	public OpenPdvDto(Long id, String login, String password, String pdvValue) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.pdvValue = pdvValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPdvValue() {
		return pdvValue;
	}

	public void setPdvValue(String pdvValue) {
		this.pdvValue = pdvValue;
	}

}
