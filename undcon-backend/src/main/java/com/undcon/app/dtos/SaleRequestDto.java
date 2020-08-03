package com.undcon.app.dtos;

public class SaleRequestDto {

	private Long id;
	private CustomerDto customer;
	private EmployeeDto salesman;

	public SaleRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public SaleRequestDto(Long id, CustomerDto customer, EmployeeDto salesman) {
		super();
		this.id = id;
		this.customer = customer;
		this.salesman = salesman;
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

}
