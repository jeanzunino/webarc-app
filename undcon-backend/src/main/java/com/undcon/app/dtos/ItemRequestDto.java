package com.undcon.app.dtos;

public class ItemRequestDto {

	private Long id;
	private Long itemId;
	private Long quantity;
	private Long employeeId;

	public ItemRequestDto() {
	}

	public ItemRequestDto(long id, long itemId, long quantity, long employeeId) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.quantity = quantity;
		this.employeeId = employeeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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
