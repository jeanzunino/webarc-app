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
@Table(name="anexos")
public class Anexo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAnexos", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "anexo")
	private String anexo;
	
	@Column(name = "thumb")
	private String thumb;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "path")
	private String path;
	
	@ManyToOne
	@JoinColumn(name = "os_id", nullable = false)
	private OrdemServico os;
}
