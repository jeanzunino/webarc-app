package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.SaleStatus;

public class SaleSimpleDto {

	private Long id;

	private Date saleDate;

	private SaleStatus status;

	public SaleSimpleDto(Long id, Date saleDate, SaleStatus status) {
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

	public SaleStatus getStatus() {
		return status;
	}

}
