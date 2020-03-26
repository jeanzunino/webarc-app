package com.undcon.app.enums;

public enum UndconError {

	NAME_ALREADY_EXISTS("O nome informado já existe"),
	
	NEW_REGISTER_INVALID_ID("O novo registro a ser salvo não pode ter o id preenchido"),
	
	PERMISSION_DENIED_TO_RESOURCE("Permissão negada para o recurso"),
	
	SALE_ENTITY_INVALID_CLIENT("Cliente inválido para a venda"),
	
	SALE_NOT_FOUND("Venda não encontrada"),
	
	SALE_ITEM_NOT_FOUND("Item de venda não encontrado"),
	
	SALE_ITEM_NOT_FOUND_IN_THE_SALE("Item de venda não encontrado na venda informada"),
	
	SALE_ITEM_STOCK_NOT_AVAIABLE("Saldo não disponível"),
	
	PURCHASE_ENTITY_INVALID_PROVIDER("Fornecedor inválido para a compra");
	
	private String message;

	private UndconError(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
