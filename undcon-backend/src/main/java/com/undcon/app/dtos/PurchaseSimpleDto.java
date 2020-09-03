package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.BillingStatus;

public class PurchaseSimpleDto {

	private Long id;

	private Date purchaseDate;

	private BillingStatus status;

	public PurchaseSimpleDto(Long id, Date purchaseDate, BillingStatus status) {
		super();
		this.id = id;
		this.purchaseDate = purchaseDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public BillingStatus getStatus() {
		return status;
	}

}
