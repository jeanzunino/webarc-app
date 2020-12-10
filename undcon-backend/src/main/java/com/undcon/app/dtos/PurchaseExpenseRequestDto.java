package com.undcon.app.dtos;

import java.sql.Date;

import com.undcon.app.enums.PaymentType;

public class PurchaseExpenseRequestDto {

	private Date duaDate;

	private Date paymentDate;

	private double value;

	private boolean settled;

	private PaymentType paymentType;

	private BankCheckDto check;

	public PurchaseExpenseRequestDto() {
	}

	public PurchaseExpenseRequestDto(Date duaDate, Date paymentDate, double value, boolean settled, PaymentType paymentType,
			BankCheckDto check) {
		super();
		this.duaDate = duaDate;
		this.paymentDate = paymentDate;
		this.value = value;
		this.settled = settled;
		this.paymentType = paymentType;
		this.check = check;
	}

	public Date getDuaDate() {
		return duaDate;
	}

	public void setDuaDate(Date duaDate) {
		this.duaDate = duaDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public BankCheckDto getCheck() {
		return check;
	}

	public void setCheck(BankCheckDto check) {
		this.check = check;
	}

}
