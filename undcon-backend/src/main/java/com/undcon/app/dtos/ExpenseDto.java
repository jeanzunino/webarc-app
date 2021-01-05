package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.PaymentType;

public class ExpenseDto {

	private Long id;

	private String description;

	private Date duaDate;

	private Date paymentDate;

	private double value;

	private PaymentStatus paymentStatus;

	private PaymentType paymentType;

	private IdDto purchase;

	private PersonSimpleDto provider;

	public ExpenseDto() {
	}

	public ExpenseDto(Long id, String description, Date duaDate, Date paymentDate, double value,
			PaymentStatus paymentStatus, PaymentType paymentType, IdDto purchase, PersonSimpleDto provider) {
		super();
		this.id = id;
		this.description = description;
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.paymentStatus = paymentStatus;
		this.paymentType = paymentType;
		this.purchase = purchase;
		this.provider = provider;
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

	public IdDto getPurchase() {
		return purchase;
	}

	public PersonSimpleDto getProvider() {
		return provider;
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

	public void setPurchase(IdDto purchase) {
		this.purchase = purchase;
	}

	public void setProvider(PersonSimpleDto provider) {
		this.provider = provider;
	}

}
