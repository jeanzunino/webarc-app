package com.undcon.app.dtos;

public class PersonSimpleDto {
	
	private Long id;

	private String name;

	public PersonSimpleDto() {
	}

	public PersonSimpleDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
