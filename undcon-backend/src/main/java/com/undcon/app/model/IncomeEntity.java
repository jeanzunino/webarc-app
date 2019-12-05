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
	private String value;

	@Column(name = "baixado")
	private int settled;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "forma_pgto")
	private String paymentType;

	public IncomeEntity() {
		// TODO Auto-generated constructor stub
	}

	public IncomeEntity(Long id, String description, Date duaDate, Date paymentDate, String value, int settled,
			CustomerEntity customer, String paymentType) {
		super();
		this.id = id;
		this.description = description;
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.settled = settled;
		this.customer = customer;
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

	public String getValue() {
		return value;
	}

	public int getSettled() {
		return settled;
	}

	public CustomerEntity getProvider() {
		return customer;
	}

	public String getPaymentType() {
		return paymentType;
	}

}
