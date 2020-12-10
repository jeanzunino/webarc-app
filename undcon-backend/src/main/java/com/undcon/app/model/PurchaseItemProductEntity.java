package com.undcon.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_compra_produto")
public class PurchaseItemProductEntity extends PurchaseItemEntity {

	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private ProductEntity product;

	public PurchaseItemProductEntity() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseItemProductEntity(Long id, ProductEntity product, PurchaseEntity purchase, UserEntity user, double price, long quantity) {
		super(id, purchase, user, price, quantity);
		this.product = product;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
