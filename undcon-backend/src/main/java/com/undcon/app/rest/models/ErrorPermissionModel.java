package com.undcon.app.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

import com.undcon.app.enums.ResourseType;

@XmlRootElement(name = "errorMessage")
public class ErrorPermissionModel {

	private ResourseType resoure;
	private String message;

	public ErrorPermissionModel() {
	}

	public ErrorPermissionModel(ResourseType resoure, String message) {
		super();
		this.resoure = resoure;
		this.message = message;
	}

	public ResourseType getResoure() {
		return resoure;
	}

	public void setResoure(ResourseType resoure) {
		this.resoure = resoure;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
