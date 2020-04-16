package com.undcon.app.exceptions;

import com.undcon.app.enums.ResourceType;

public class UndconPermissionException extends Exception {

	private static final long serialVersionUID = -486222184640816768L;
	private ResourceType resourseType;

	public UndconPermissionException(ResourceType resourseType) {
		super();
		this.resourseType = resourseType;
	}

	public ResourceType getResourseType() {
		return resourseType;
	}
}
