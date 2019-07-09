package com.webarc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="produtos")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProdutos", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "unidade")
	private String unidade;
	
	@Column(name = "precoCompra")
	private double precoCompra;
	
	@Column(name = "precoVenda")
	private double precoVenda;

	@Column(name = "estoque")
	private int estoque;
	
	@Column(name = "estoqueMinimo")
	private int estoqueMinimo;
}
