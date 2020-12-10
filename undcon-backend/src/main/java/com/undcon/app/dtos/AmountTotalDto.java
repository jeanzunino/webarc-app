package com.undcon.app.dtos;

public class AmountTotalDto {

	private Double totalValue;
	private double amountPayable;
	private double amountPaid;

	public AmountTotalDto(Double totalValue, double amountPayable, double amountPaid) {
		super();
		this.totalValue = totalValue;
		this.amountPayable = amountPayable;
		this.amountPaid = amountPaid;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public double getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

}
