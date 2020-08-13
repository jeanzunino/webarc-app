package com.undcon.app.dtos;

import com.undcon.app.enums.SaleStatus;

public class SaleRequestDto {

	private Long id;
	private PersonDto customer;
	private PersonDto salesman;
	private SaleStatus status;
	

	public SaleRequestDto() {
	}

	public SaleRequestDto(Long id, PersonDto customer, PersonDto salesman, SaleStatus status) {
		super();
		this.id = id;
		this.customer = customer;
		this.salesman = salesman;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonDto getCustomer() {
		return customer;
	}

	public void setCustomer(PersonDto customer) {
		this.customer = customer;
	}

	public PersonDto getSalesman() {
		return salesman;
	}

	public void setSalesman(PersonDto salesman) {
		this.salesman = salesman;
	}
	
	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

}
