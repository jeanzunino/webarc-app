package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "menu_template")
public class MenuTemplateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", nullable = true)
	private String name;
	
	@Column(name = "template_padrao", nullable = false)
	private boolean defaultTemplate;
	
	public MenuTemplateEntity() {
	}

	public MenuTemplateEntity(Long id, String name, boolean defaultTemplate) {
		super();
		this.id = id;
		this.name = name;
		this.defaultTemplate = defaultTemplate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isDefaultTemplate() {
		return defaultTemplate;
	}
	
}
