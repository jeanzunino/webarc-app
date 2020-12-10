package com.undcon.app.dtos;

public class PersonDto extends PersonSimpleDto{

	private String phone;

	public PersonDto() {
	}

	public PersonDto(Long id, String name, String phone) {
		super(id, name);
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

}
