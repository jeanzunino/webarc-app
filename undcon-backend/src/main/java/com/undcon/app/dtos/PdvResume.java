package com.undcon.app.dtos;

public class PdvResume {

	private PdvDto pdv;
	private double saleValue;

	public PdvDto getPdv() {
		return pdv;
	}

	public double getSaleValue() {
		return saleValue;
	}

	public void setPdv(PdvDto pdv) {
		this.pdv = pdv;
	}

	public void setSaleValue(double saleValue) {
		this.saleValue = saleValue;
	}
}
