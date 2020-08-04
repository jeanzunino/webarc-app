package com.undcon.app.dtos;

public class SaleTotalDto {

	private Double totalValue;

	public SaleTotalDto(Double totalValue) {
		super();
		this.totalValue = totalValue;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
}
