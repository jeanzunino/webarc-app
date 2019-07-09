package com.webarc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itens_de_compras")
public class ItemVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idItens", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "subTotal")
	private String subTotal;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name = "vendas_id", nullable = false)
	private Sale venda;
	
	@ManyToOne
	@JoinColumn(name = "produtos_id", nullable = false)
	private Sale produto;
	
}
