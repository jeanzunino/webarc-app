package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.SaleStatus;

public class SaleSimpleDto {

	private Long id;

	private Date saleDate;

	private Boolean billed;

	private SaleStatus status;

	private IdDto customer;

	private IdDto user;

	private IdDto salesman;

	public SaleSimpleDto(Long id, Date saleDate, Boolean billed, SaleStatus status, IdDto customer, IdDto user,
			IdDto salesman) {
		super();
		this.id = id;
		this.saleDate = saleDate;
		this.billed = billed;
		this.status = status;
		this.customer = customer;
		this.user = user;
		this.salesman = salesman;
	}

	public Long getId() {
		return id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public Boolean getBilled() {
		return billed;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public IdDto getCustomer() {
		return customer;
	}

	public IdDto getUser() {
		return user;
	}

	public IdDto getSalesman() {
		return salesman;
	}

}
