package com.undcon.app.dtos;

public class ProductSaledInfoDto {

	private Long productId;
	private String productName;
	private Long quantity;
	private Double totalSaled;

	public ProductSaledInfoDto(Long productId, String productName, Long quantity, Double totalSaled) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.totalSaled = totalSaled;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getTotalSaled() {
		return totalSaled;
	}

	public void setTotalSaled(Double totalSaled) {
		this.totalSaled = totalSaled;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
