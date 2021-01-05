package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.PaymentType;

public class IncomeDto {

	private Long id;

	private String description;

	private Date duaDate;

	private Date paymentDate;

	private double value;

	private PaymentStatus paymentStatus;

	private PaymentType paymentType;

	private IdDto sale;

	private PersonSimpleDto customer;

	public IncomeDto() {
	}

	public IncomeDto(Long id, String description, Date duaDate, Date paymentDate, double value,
			PaymentStatus paymentStatus, PaymentType paymentType, IdDto sale, PersonSimpleDto customer) {
		super();
		this.id = id;
		this.description = description;
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.paymentStatus = paymentStatus;
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

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public IdDto getSale() {
		return sale;
	}

	public PersonSimpleDto getCustomer() {
		return customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDuaDate(Date duaDate) {
		this.duaDate = duaDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public void setSale(IdDto sale) {
		this.sale = sale;
	}

	public void setCustomer(PersonSimpleDto customer) {
		this.customer = customer;
	}

}
