package com.undcon.app.dtos;

import com.undcon.app.enums.SaleStatus;

public class SaleRequestDto {

	private Long id;
	private CustomerDto customer;
	private EmployeeDto salesman;
	private SaleStatus status;
	

	public SaleRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public SaleRequestDto(Long id, CustomerDto customer, EmployeeDto salesman, SaleStatus status) {
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

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public EmployeeDto getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeDto salesman) {
		this.salesman = salesman;
	}
	
	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

}
