package com.undcon.app.enums;

public enum BillingStatus {

	/**
	 * 0 - A venda/compra foi criada e pode estar sendo realizada, incluindo itens
	 */
	CREATED,

	/**
	 * 1 - A venda/compra foi finalizada e e enviada para faturamento, então já pode
	 * ser faturada
	 */
	TO_BILL,

	/**
	 * 2 - Faturado Parcialmente - Valor faturado da venda (Receitas da venda) é
	 * menor que o valor total da venda
	 */
	BILLED,

	/**
	 * 3 - Faturado Totalmente - Valor faturado da venda/compra (Receitas da venda/compra) sem juros é igual ao valor da venda
	 */
	TOTAL_BILLED,

	/** 4 - Venda Cancelada */
	CANCELED
}
