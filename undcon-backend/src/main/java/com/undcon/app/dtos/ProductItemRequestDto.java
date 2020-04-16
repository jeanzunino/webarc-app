package com.undcon.app.dtos;

public class ProductItemRequestDto {

	private Long id;
	private Long productId;
	private Long quantity;
	private Long employeeId;

	public ProductItemRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductItemRequestDto(long id, long productId, long quantity, long employeeId) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.employeeId = employeeId;
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
