package com.undcon.app.dtos;

import java.util.List;

public class SaleIncomeResponseDto {

	private List<IncomeDto> incomesCreated;

	private SaleSimpleDto sale;

	private double amountPayable;

	private double amountPaid;

	public SaleIncomeResponseDto(List<IncomeDto> incomesCreated, SaleSimpleDto sale, double amountPayable, double amountPaid) {
		super();
		this.incomesCreated = incomesCreated;
		this.sale = sale;
		this.amountPayable = amountPayable;
		this.amountPaid = amountPaid;
	}

	public List<IncomeDto> getIncomesCreated() {
		return incomesCreated;
	}

	public SaleSimpleDto getSale() {
		return sale;
	}

	public double getAmountPayable() {
		return amountPayable;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setIncomesCreated(List<IncomeDto> incomesCreated) {
		this.incomesCreated = incomesCreated;
	}

	public void setSale(SaleSimpleDto sale) {
		this.sale = sale;
	}

	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

}
