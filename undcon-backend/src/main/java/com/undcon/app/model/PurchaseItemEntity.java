package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public abstract class PurchaseItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "compra_id", nullable = false)
	private PurchaseEntity purchase;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	private EmployeeEntity salesman;

	@Column(name = "valor_unitario")
	private double price;

	@Column(name = "quantidade")
	private long quantity;

	public PurchaseItemEntity() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseItemEntity(Long id, PurchaseEntity purchase, UserEntity user, EmployeeEntity salesman,
			double price, long quantity) {
		super();
		this.id = id;
		this.purchase = purchase;
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

	public PurchaseEntity getSale() {
		return purchase;
	}

	public void setSale(PurchaseEntity purchase) {
		this.purchase = purchase;
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
