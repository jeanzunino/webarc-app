package com.undcon.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "tenants")
public class TenantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome_cliente")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String phone;

	@Column(name = "schema_name")
	private String schemaName;

	@Column(name = "data_cadastro")
	private Timestamp registrationDate;

	protected TenantEntity() {
	}

	public TenantEntity(Long id, String name, String email, String phone, String schemaName, Timestamp registrationDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.schemaName = schemaName;
		this.registrationDate = registrationDate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}
 
}
