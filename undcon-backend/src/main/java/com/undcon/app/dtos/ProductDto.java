package com.undcon.app.dtos;

public class ProductDto {

	private Long id;
	private String name;
	private String unit;
	private String gtin;
	private ProductCategoryDto productCategory;
	private double purchasePrice;
	private double salePrice;
	private long stock;
	private long stockMin;

	public ProductDto() {
	}

	public ProductDto(Long id, String name, String unit, String gtin, ProductCategoryDto productCategory,
			double purchasePrice, double salePrice, long stock, long stockMin) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.gtin = gtin;
		this.productCategory = productCategory;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.stock = stock;
		this.stockMin = stockMin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public ProductCategoryDto getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryDto productCategory) {
		this.productCategory = productCategory;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public long getStockMin() {
		return stockMin;
	}

	public void setStockMin(long stockMin) {
		this.stockMin = stockMin;
	}

	
}
