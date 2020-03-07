package com.undcon.app.exceptions;

import com.undcon.app.enums.UndconError;

public class UndconException extends Exception {

	private static final long serialVersionUID = -486222184640816768L;
	private UndconError error;

	public UndconException(UndconError error) {
		super();
		this.error = error;
	}

	public UndconError getError() {
		return error;
	}

}
