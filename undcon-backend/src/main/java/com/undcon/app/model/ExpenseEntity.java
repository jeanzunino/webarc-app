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
@Table(name = "despesa")
public class ExpenseEntity {

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
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private ProviderEntity provider;

	@Column(name = "forma_pgto")
	private PaymentType paymentType;

	public ExpenseEntity() {
		// TODO Auto-generated constructor stub
	}

	public ExpenseEntity(Long id, String description, Date duaDate, Date paymentDate, double value,
			PaymentStatus paymentStatus, ProviderEntity provider, PaymentType paymentType) {
		super();
		this.id = id;
		this.description = description;
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.paymentStatus = paymentStatus;
		this.provider = provider;
		this.paymentType = paymentType;
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

	public ProviderEntity getProvider() {
		return provider;
	}

	public PaymentType getPaymentType() {
		return paymentType;
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

	public void setProvider(ProviderEntity provider) {
		this.provider = provider;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
