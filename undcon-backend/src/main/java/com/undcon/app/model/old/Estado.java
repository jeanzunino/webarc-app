package com.undcon.app.model.old;

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
@Table(name="estados")
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstado", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sigla")
	private String sigla;
}
