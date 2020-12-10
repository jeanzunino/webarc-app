package com.undcon.app.dtos;

import java.util.List;

public class PurchaseExpenseResponseDto {

	private List<ExpenseDto> expensesCreated;

	private PurchaseSimpleDto purchase;

	private double amountPayable;

	private double amountPaid;

	public PurchaseExpenseResponseDto(List<ExpenseDto> expensesCreated, PurchaseSimpleDto purchase,
			double amountPayable, double amountPaid) {
		super();
		this.expensesCreated = expensesCreated;
		this.purchase = purchase;
		this.amountPayable = amountPayable;
		this.amountPaid = amountPaid;
	}

	public List<ExpenseDto> getExpensesCreated() {
		return expensesCreated;
	}

	public double getAmountPayable() {
		return amountPayable;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setExpensesCreated(List<ExpenseDto> expensesCreated) {
		this.expensesCreated = expensesCreated;
	}

	public PurchaseSimpleDto getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseSimpleDto purchase) {
		this.purchase = purchase;
	}

	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

}
