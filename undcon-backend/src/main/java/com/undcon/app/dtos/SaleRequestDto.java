package com.undcon.app.dtos;

public class SaleRequestDto {

	private Long id;
	private Long customerId;
	private Long salesmanId;

	public SaleRequestDto() {
		// TODO Auto-generated constructor stub
	}
	public SaleRequestDto(Long id, Long customerId, Long salesmanId) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.salesmanId = salesmanId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Long salesmanId) {
		this.salesmanId = salesmanId;
	}

}
