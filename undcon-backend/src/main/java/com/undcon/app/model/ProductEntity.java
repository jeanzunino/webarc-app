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
	@JoinColumn(name = "categoria_produto_id", nullable = false)
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

	public void setStock(long stock) {
		this.stock = stock;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public void setStockMin(long stockMin) {
		this.stockMin = stockMin;
	}

	public void setProductCategory(ProductCategoryEntity productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
