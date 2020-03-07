package com.undcon.app.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

import com.undcon.app.enums.UndconError;

@XmlRootElement(name = "errorMessage")
public class ErrorMessageModel {

	private UndconError translationKey;
	private String message;

	public ErrorMessageModel() {
	}

	public ErrorMessageModel(UndconError error) {
		super();
		this.translationKey = error;
		this.message = error.getMessage();
	}

	public ErrorMessageModel(UndconError error, String message) {
		super();
		this.translationKey = error;
		this.message = message;
	}
	
	public UndconError getTranslationKey() {
		return translationKey;
	}

	public void setTranslationKey(UndconError translationKey) {
		this.translationKey = translationKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
