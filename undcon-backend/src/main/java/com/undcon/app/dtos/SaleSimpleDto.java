package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.SaleStatus;

public class SaleSimpleDto {

	private Long id;

	private Date saleDate;

	private SaleStatus status;

	private IdDto customer;

	private IdDto user;

	private IdDto salesman;

	public SaleSimpleDto(Long id, Date saleDate, SaleStatus status, IdDto customer, IdDto user,
			IdDto salesman) {
		super();
		this.id = id;
		this.saleDate = saleDate;
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
