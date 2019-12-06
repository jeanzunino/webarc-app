package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuracao")
public class ConfigurationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "logo")
	private byte[] logo;

	protected ConfigurationEntity() {
	}

	public ConfigurationEntity(Long id, byte[] logo) {
		super();
		this.id = id;
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public byte[] getLogo() {
		return logo;
	}
	
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	
}