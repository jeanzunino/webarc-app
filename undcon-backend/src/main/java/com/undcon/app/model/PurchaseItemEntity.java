package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PurchaseItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
	@SequenceGenerator(name = "item_id_seq", sequenceName = "item_compra_produto_id_seq")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "compra_id", nullable = false)
	private PurchaseEntity purchase;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UserEntity user;

	@Column(name = "valor_unitario")
	private double price;

	@Column(name = "quantidade")
	private long quantity;

	public PurchaseItemEntity() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseItemEntity(Long id, PurchaseEntity purchase, UserEntity user, double price, long quantity) {
		super();
		this.id = id;
		this.purchase = purchase;
		this.user = user;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PurchaseEntity getPurchase() {
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

}
