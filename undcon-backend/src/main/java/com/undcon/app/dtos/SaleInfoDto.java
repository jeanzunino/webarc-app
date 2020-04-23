package com.undcon.app.dtos;

public class SaleInfoDto {

	private Double totalSaleBilled;
	private Double totalSaleNoBilled;

	public SaleInfoDto(Double totalSaleBilled, Double totalSaleNoBilled) {
		super();
		this.totalSaleBilled = totalSaleBilled;
		this.totalSaleNoBilled = totalSaleNoBilled;
	}

	public Double getTotalSaleBilled() {
		return totalSaleBilled;
	}

	public void setTotalSaleBilled(Double totalSale) {
		this.totalSaleBilled = totalSale;
	}

	public Double getTotalSaleNoBilled() {
		return totalSaleNoBilled;
	}

	public void setTotalSaleNoBilled(Double totalSaleNoBilled) {
		this.totalSaleNoBilled = totalSaleNoBilled;
	}
}
