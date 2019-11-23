package com.undcon.app.model;

import javax.persistence.*;

@Entity
@Table(name = "fornecedores")
public class ProviderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "telefone")
	private String phone;

	protected ProviderEntity() {
	}

	public ProviderEntity(Long id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}
}