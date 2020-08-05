package com.undcon.app.dtos;

import com.undcon.app.model.IncomeEntity;
import com.undcon.app.model.SaleEntity;

public class SaleIncomeResponseDto {

	private IncomeEntity income;

	private SaleEntity sale;

	private double amountPayable;

	private double amountPaid;

	public SaleIncomeResponseDto(IncomeEntity income, SaleEntity sale, double amountPayable, double amountPaid) {
		super();
		this.income = income;
		this.sale = sale;
		this.amountPayable = amountPayable;
		this.amountPaid = amountPaid;
	}

	public IncomeEntity getIncome() {
		return income;
	}

	public void setIncome(IncomeEntity income) {
		this.income = income;
	}

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
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
