package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
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

	public ProductEntity() {
	}

	public ProductEntity(Long id, String name, String unit, double purchasePrice, double salePrice, long stock,
			long stockMin) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.stock = stock;
		this.stockMin = stockMin;
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

}
