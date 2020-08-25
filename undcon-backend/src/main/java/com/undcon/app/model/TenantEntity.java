package com.undcon.app.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.undcon.app.enums.ClientStatus;

@Entity
@Table(schema = "public", name = "tenant")
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
	
	@Column(name = "status", nullable = false)
	private ClientStatus status;
	
	@ManyToOne
	@JoinColumn(name = "menu_template_id", nullable = true)
	private MenuTemplateEntity menu;

	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	private SystemSalesmanEntity salesman;
	
	protected TenantEntity() {
	}

	public TenantEntity(Long id, String name, String email, String phone, String schemaName, Timestamp registrationDate,
			MenuTemplateEntity menu, SystemSalesmanEntity salesman, ClientStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.schemaName = schemaName;
		this.registrationDate = registrationDate;
		this.menu = menu;
		this.salesman = salesman;
		this.status = status;
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
	
	public SystemSalesmanEntity getSalesman() {
		return salesman;
	}
	
	public void setSalesman(SystemSalesmanEntity salesman) {
		this.salesman = salesman;
	}
	
	public ClientStatus getStatus() {
		return status;
	}
	
	public void setStatus(ClientStatus status) {
		this.status = status;
	}
 
}
