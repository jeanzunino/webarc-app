package com.undcon.app.enums;

public enum UndconError {

	API_ERROR_INVALID_PARAMETERS("Ocorreu um erro na API"),
	
	GENERIC_ERROR("Erro gerérico não tratado no Backend"),
	
	INVALID_USER_OR_PASSWORD("Usuário ou senha inválidos"),
	
	USER_BLOCKED("O usuário está bloqueado"),
	
	INVALID_USER_LOGGED("Usuário logado está inválido, inativo, ou foi excluído"),
	
	INVALID_TOKEN_RETRY_LOGIN("Token inválido. Tente logar novamente."),
	
	INVALID_TOKEN_UPDATE_PASSWORD("Token foi gerado para alterar a senha. Altere a senha e tente logar novamente."),
	
	INVALID_LOGIN_FORMAT_WITH_DOMAIN("Usuário inválido: o usuário deve estar no formato 'usuario@cliente'"),
	
	INVALID_LOGIN_FORMAT("Usuário inválido: o login do usuário deve ter 3 ou mais caracteres e não pode ter @"),
	
	NAME_ALREADY_EXISTS("O nome informado já existe"),
	
	LOGIN_ALREADY_EXISTS("O login informado já existe"),
	
	LOGIN_ALREADY_EXISTS_IN_EMPLOYEE("O login já foi informado para este colaborador"),
	
	NEW_REGISTER_INVALID_ID("O novo registro a ser salvo não pode ter o id preenchido"),
	
	PERMISSION_DENIED_TO_RESOURCE("Permissão negada para o recurso"),
	
	SALE_ENTITY_INVALID_CLIENT("Cliente inválido para a venda"),
	
	SALE_NOT_FOUND("Venda não encontrada"),
	
	PERMISSION_NOT_FOUND("Permissão não encontrada"),
	
	EMPLOYEE_NOT_FOUND("Funcionário não encontrado"),
	
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
	
	public String getTranslationKey(){
		return "backend." + name().toLowerCase().replace("_", ".");
	}
	
}
