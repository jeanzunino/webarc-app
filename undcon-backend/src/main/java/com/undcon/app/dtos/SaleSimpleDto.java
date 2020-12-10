package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.BillingStatus;

public class SaleSimpleDto {

	private Long id;

	private Date saleDate;

	private BillingStatus status;

	public SaleSimpleDto(Long id, Date saleDate, BillingStatus status) {
		super();
		this.id = id;
		this.saleDate = saleDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public BillingStatus getStatus() {
		return status;
	}

}
