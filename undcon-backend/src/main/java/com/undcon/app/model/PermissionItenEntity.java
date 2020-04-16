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
@Table(name = "permissao_item")
public class PermissionItenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "permissao_id", nullable = false)
	private PermissionEntity permission;
	
	@Column(name = "tipo", nullable = false)
	private ResourceType resourceType;
	
	public PermissionItenEntity() {
	}

	public PermissionItenEntity(Long id, PermissionEntity permission, ResourceType resourceType) {
		super();
		this.id = id;
		this.permission = permission;
		this.resourceType = resourceType;
	}

	public Long getId() {
		return id;
	}

	public PermissionEntity getPermission() {
		return permission;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

}
