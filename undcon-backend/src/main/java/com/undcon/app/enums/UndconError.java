package com.undcon.app.enums;

public enum UndconError {

	NAME_ALREADY_EXISTS("O nome informado já existe"),
	
	NEW_REGISTER_INVALID_ID("O novo registro a ser salvo não pode ter o id preenchido"),
	
	PERMISSION_DENIED_TO_RESOURCE("Permissão negada para o recurso");
	
	private String message;

	private UndconError(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
