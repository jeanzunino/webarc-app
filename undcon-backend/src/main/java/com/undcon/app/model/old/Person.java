package com.undcon.app.model.old;

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
@Table(name="pessoas")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pessoa_id", nullable = false, unique = true)
    private Long id;
	
	@Column(name = "nomeCliente")
	private String nome;
	
	@Column(name = "documento")
	private String documento;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "celular")
	private String celular;

	@Column(name = "email")
	private String email;
	
	@Column(name = "dataCadastro")
	private Date dataCadastro;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "bairro")
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@Column(name = "cep")
	private String cep;
	
}
