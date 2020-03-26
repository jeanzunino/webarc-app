package com.undcon.app.dtos;

public class ProductItemRequestDto {

	private Long id;
	private Long productId;
	private Long quantity;
	private Long salesmanId;

	public ProductItemRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductItemRequestDto(long id, long productId, long quantity, long salesmanId) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.salesmanId = salesmanId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}

}
