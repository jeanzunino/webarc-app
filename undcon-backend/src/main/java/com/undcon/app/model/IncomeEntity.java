package com.undcon.app.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.PaymentType;

@Entity
@Table(name = "receita")
public class IncomeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "descricao")
	private String description;

	@Column(name = "data_vencimento", nullable = false)
	private Date duaDate;

	@Column(name = "data_pagamento", nullable = false)
	private Date paymentDate;

	@Column(name = "valor")
	private double value;

	@Column(name = "status")
	private PaymentStatus paymentStatus;

	@ManyToOne
	@JoinColumn(name = "venda_id", nullable = true)
	private SaleEntity sale;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "forma_pgto")
	private PaymentType paymentType;

	public IncomeEntity() {
	}

	public IncomeEntity(Long id, String description, Date duaDate, Date paymentDate, double value,
			PaymentStatus paymentStatus, SaleEntity sale, CustomerEntity customer, PaymentType paymentType) {
		super();
		this.id = id;
		this.description = description;
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.paymentStatus = paymentStatus;
		this.sale = sale;
		this.customer = customer;
		this.paymentType = paymentType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDuaDate() {
		return duaDate;
	}

	public void setDuaDate(Date duaDate) {
		this.duaDate = duaDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
