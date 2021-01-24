package com.undcon.app.enums;

public enum IntervalType {

	MONTHLY, WEEKLY, DIARY;

	public String getGroupBy() {
		switch (this) {
		case MONTHLY:
			return "'YYYY-MM'";
		case WEEKLY:
			return "'YYYY-MM Semana W'";
		case DIARY:
			return "'YYYY-MM-DD'";
		default:
			throw new IllegalArgumentException("Tipo de interval não tratado: " + this);
		}
	}
}
