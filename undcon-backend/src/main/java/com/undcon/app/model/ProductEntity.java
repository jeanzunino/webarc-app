package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "unidade")
	private String unit;

	@Column(name = "preco_compra")
	private double purchasePrice;

	@Column(name = "preco_venda")
	private double salePrice;

	@Column(name = "estoque")
	private long stock;

	@Column(name = "estoque_minimo")
	private long stockMin;
	
	@ManyToOne
	@JoinColumn(name = "categoria_produto", nullable = false)
	private ProductCategoryEntity productCategory;

	public ProductEntity() {
	}

	public ProductEntity(Long id, String name, String unit, double purchasePrice, double salePrice, long stock,
			long stockMin, ProductCategoryEntity productCategory) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.stock = stock;
		this.stockMin = stockMin;
		this.productCategory = productCategory;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public long getStock() {
		return stock;
	}

	public long getStockMin() {
		return stockMin;
	}

	public ProductCategoryEntity getProductCategory() {
		return productCategory;
	}
}
