package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="servicos")
public class ServiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idServicos", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "preco")
	private double preco;
}
