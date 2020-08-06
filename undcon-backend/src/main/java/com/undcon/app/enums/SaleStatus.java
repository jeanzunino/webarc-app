package com.undcon.app.enums;

public enum SaleStatus {

	/** 0 - A venda foi criada */
	CREATED,

	/** 1 - A venda foi finalizada e já pode ser faturada */
	TO_BILL,

	/**
	 * 2 - Faturado Parcialmente - Valor faturado da venda (Receitas da venda) é
	 * menor que o valor da venda
	 */
	BILLED,

	/**
	 * 3 - Faturado Totalmente - Valor faturado da venda (Receitas da venda) sem
	 * juros é igual ao valor da venda
	 */
	TOTAL_BILLED,

	/** 4 - Venda Cancelada */
	CANCELED
}
