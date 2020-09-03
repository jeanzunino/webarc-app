package com.undcon.app.dtos;

public class PurchaseRequestDto {

	private Long id;
	private PersonDto provider;

	public PurchaseRequestDto() {
	}

	public PurchaseRequestDto(Long id, PersonDto provider) {
		super();
		this.id = id;
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonDto getProvider() {
		return provider;
	}

	public void setProvider(PersonDto provider) {
		this.provider = provider;
	}

}
