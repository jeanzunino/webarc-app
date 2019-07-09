package com.webarc.app.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Accessors(fluent = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuarios", nullable = false, unique = true)
    private long id;
	
	private String nome;
	
	private String email;
//	private String cpf;
//	private String rua;
//	private String numero;
//	private String bairro;
//	private String senha;
//	private String celular;
//	private int situacao;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id", nullable = true)
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "permissoes_id", nullable = true)
	private Permission permissao;
	
	@Column(name = "dataCadastro", nullable = false)
	private Date dataInicial;
	
}
