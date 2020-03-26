package com.undcon.app.exceptions;

import com.undcon.app.enums.ResourseType;

public class UndconPermissionException extends Exception {

	private static final long serialVersionUID = -486222184640816768L;
	private ResourseType resourseType;

	public UndconPermissionException(ResourseType resourseType) {
		super();
		this.resourseType = resourseType;
	}

	public ResourseType getResourseType() {
		return resourseType;
	}
}
