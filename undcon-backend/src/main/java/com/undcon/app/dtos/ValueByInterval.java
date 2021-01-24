package com.undcon.app.dtos;

public class ValueByInterval {

	private String interval;
	private Double value;

	public ValueByInterval() {
	}

	
	public ValueByInterval(String interval, Double value) {
		super();
		this.interval = interval;
		this.value = value;
	}


	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
