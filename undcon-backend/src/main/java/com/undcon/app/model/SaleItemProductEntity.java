package com.undcon.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_venda_produto")
public class SaleItemProductEntity extends SaleItemEntity {

	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private ProductEntity product;

	public SaleItemProductEntity() {
	}

	public SaleItemProductEntity(Long id, ProductEntity product, SaleEntity sale, UserEntity user,
			EmployeeEntity salesman, double price, long quantity) {
		super(id, sale, user, salesman, price, quantity);
		this.product = product;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
