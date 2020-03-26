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

import com.undcon.app.enums.SaleStatus;

@Entity
@Table(name = "venda")
public class SaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private CustomerEntity customer;

	@Column(name = "data_venda", nullable = false)
	private Date saleDate;

	@Column(name = "faturado", nullable = false)
	private Boolean billed;

	@Column(name = "status", nullable = false)
	private SaleStatus status;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	private EmployeeEntity salesman;

	public SaleEntity() {
		// TODO Auto-generated constructor stub
	}

	public SaleEntity(Long id, CustomerEntity customer, Date saleDate, boolean billed, SaleStatus status,
			UserEntity user, EmployeeEntity salesman) {
		super();
		this.id = id;
		this.customer = customer;
		this.saleDate = saleDate;
		this.billed = billed;
		this.status = status;
		this.user = user;
		this.salesman = salesman;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public EmployeeEntity getSalesman() {
		return salesman;
	}

	public void setSalesman(EmployeeEntity salesman) {
		this.salesman = salesman;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public boolean isBilled() {
		return billed;
	}

	public void setBilled(boolean billed) {
		this.billed = billed;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

}
