package com.undcon.app.model.old;

import java.sql.Date;

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
@Table(name = "permissoes")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPermissao", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "permissoes")
	private String permissoes;
	
	@Column(name = "situacao")
	private int situacao;
	
	@Column(name = "data", nullable = false)
	private Date date;
}
