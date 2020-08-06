package com.undcon.app.dtos;

public class SaleIncomeResponseDto {

	private IncomeDto income;

	private SaleSimpleDto sale;

	private double amountPayable;

	private double amountPaid;

	public SaleIncomeResponseDto(IncomeDto income, SaleSimpleDto sale, double amountPayable, double amountPaid) {
		super();
		this.income = income;
		this.sale = sale;
		this.amountPayable = amountPayable;
		this.amountPaid = amountPaid;
	}

	public IncomeDto getIncome() {
		return income;
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

}
