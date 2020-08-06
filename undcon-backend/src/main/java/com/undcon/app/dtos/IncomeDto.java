package com.undcon.app.dtos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.undcon.app.enums.PaymentType;
import com.undcon.app.model.CustomerEntity;
import com.undcon.app.model.SaleEntity;

public class IncomeDto {

	private Long id;

	private String description;

	private Date duaDate;

	private Date paymentDate;

	private double value;

	private boolean settled;

	private PaymentType paymentType;

	private IdDto sale;

	private IdDto customer;

	public IncomeDto(Long id, String description, Date duaDate, Date paymentDate, double value, boolean settled,
			PaymentType paymentType, IdDto sale, IdDto customer) {
		super();
		this.id = id;
		this.description = description;
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.settled = settled;
		this.paymentType = paymentType;
		this.sale = sale;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Date getDuaDate() {
		return duaDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public double getValue() {
		return value;
	}

	public boolean isSettled() {
		return settled;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public IdDto getSale() {
		return sale;
	}

	public IdDto getCustomer() {
		return customer;
	}

}
