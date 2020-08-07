package com.undcon.app.dtos;

public class BankCheckDto {
	private Long id;
	private Long number;
	private String emitter;
	private String personDocument;

	public BankCheckDto() {
	}

	public BankCheckDto(Long id, Long number, String emitter, String personDocument) {
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

	public void setDocument(String personDocument) {
		this.personDocument = personDocument;
	}

}
