package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.undcon.app.enums.ResourceType;

@Entity
@Table(schema = "public", name = "menu_template_item")
public class MenuTemplateItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "menu_template_id", nullable = false)
	private MenuTemplateEntity menu;

	@Column(name = "menu_item", nullable = false)
	private ResourceType resourceType;

	@Column(name = "ordem", nullable = false)
	private int order;

	public MenuTemplateItemEntity() {
	}

	public MenuTemplateItemEntity(Long id, MenuTemplateEntity menu, ResourceType resourceType, int order) {
		super();
		this.id = id;
		this.menu = menu;
		this.resourceType = resourceType;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public MenuTemplateEntity getMenu() {
		return menu;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public int getOrder() {
		return order;
	}

}
