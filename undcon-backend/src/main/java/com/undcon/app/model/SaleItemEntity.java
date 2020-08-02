package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SaleItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "venda_id", nullable = false)
	private SaleEntity sale;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "empregado_id", nullable = false)
	private EmployeeEntity salesman;

	@Column(name = "valor_unitario")
	private double price;

	@Column(name = "quantidade")
	private long quantity;

	public SaleItemEntity() {
		// TODO Auto-generated constructor stub
	}

	public SaleItemEntity(Long id, SaleEntity sale, UserEntity user, EmployeeEntity salesman,
			double price, long quantity) {
		super();
		this.id = id;
		this.sale = sale;
		this.user = user;
		this.salesman = salesman;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
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

}
