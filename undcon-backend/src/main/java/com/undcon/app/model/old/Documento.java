package com.undcon.app.model.old;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="documentos")
public class Documento {
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDocumentos", nullable = false, unique = true)
	private Long id;

	@Column(name = "documento")
	private String documento;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "file")
	private String file;
	
	@Column(name = "path")
	private String path;

	@Column(name = "url")
	private String url;
	
	@Column(name = "cadastro", nullable = false)
	private Date dataCadastro;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "tamanho")
	private String tamanho;
}
