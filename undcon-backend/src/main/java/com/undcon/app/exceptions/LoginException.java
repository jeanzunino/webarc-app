package com.undcon.app.exceptions;

import com.undcon.app.enums.UndconError;

public class LoginException extends UndconException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2746558257159065797L;

	public LoginException(UndconError error) {
		super(error);
	}

}
