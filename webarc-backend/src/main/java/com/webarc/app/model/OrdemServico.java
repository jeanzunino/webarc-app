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
@Table(name = "os")
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOs", nullable = false, unique = true)
	private Long id;

	@Column(name = "dataInicial", nullable = false)
	private Date dataInicial;

	@Column(name = "dataFinal", nullable = false)
	private Date dataFinal;

	@Column(name = "descricaoProduto")
	private String descricaoProduto;

	@Column(name = "defeito")
	private String defeito;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "garantia")
	private String garantia;
	
	@Column(name = "observacoes")
	private String observacoes;
	
	@Column(name = "laudoTecnico")
	private String laudoTecnico;

	@Column(name = "valorTotal")
	private String valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "clientes_id", nullable = false)
	private Person cliente;
	
	@ManyToOne
	@JoinColumn(name = "usuarios_id", nullable = false)
	private User usuario;
	
	@ManyToOne
	@JoinColumn(name = "lancamento", nullable = false)
	private Lancamento lancamento;
	
	@Column(name = "faturado", nullable = false)
	private int faturado;
}
