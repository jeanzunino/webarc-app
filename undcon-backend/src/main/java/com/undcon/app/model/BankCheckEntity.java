package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cheque_bancario")
public class BankCheckEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "numero_cheque")
	private Long number;

	@Column(name = "pessoa_nome")
	private String emitter;

	@Column(name = "pessoa_documento")
	private String personDocument;

	public BankCheckEntity() {
	}

	public BankCheckEntity(Long id, Long number, String emitter, String personDocument) {
		super();
		this.id = id;
		this.number = number;
		this.emitter = emitter;
		this.personDocument = personDocument;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getEmitter() {
		return emitter;
	}

	public void setEmitter(String emitter) {
		this.emitter = emitter;
	}

	public String getPersonDocument() {
		return personDocument;
	}

	public void setPersonDocument(String personDocument) {
		this.personDocument = personDocument;
	}

}
