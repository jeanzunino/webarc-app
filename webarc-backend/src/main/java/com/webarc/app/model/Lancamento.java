package com.webarc.app.model;

import java.sql.Date;

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
@Table(name = "lancamentos")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLancamentos", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;
	
	@Column(name = "data_pagamento", nullable = false)
	private Date dataPagamento;

	@Column(name = "valor")
	private String valor;
	
	@Column(name = "baixado")
	private int baixado;
	
	@ManyToOne
	@JoinColumn(name = "cliente_fornecedor", nullable = false)
	private Person pagador;

	@Column(name = "forma_pgto")
	private String formaPgto;

	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "anexo")
	private String anexo;
	
}
