package com.undcon.app.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

import com.undcon.app.enums.ResourceType;

@XmlRootElement(name = "errorMessage")
public class ErrorPermissionModel {

	private ResourceType resoure;
	private String message;

	public ErrorPermissionModel() {
	}

	public ErrorPermissionModel(ResourceType resoure, String message) {
		super();
		this.resoure = resoure;
		this.message = message;
	}

	public ResourceType getResoure() {
		return resoure;
	}

	public void setResoure(ResourceType resoure) {
		this.resoure = resoure;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
