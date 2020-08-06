package com.undcon.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "categoria_produto")
public class ProductCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_pai_id", nullable = true)
	private ProductCategoryEntity parent;
	
	public ProductCategoryEntity() {
	}

	public ProductCategoryEntity(Long id, String name, ProductCategoryEntity parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ProductCategoryEntity getParent() {
		return parent;
	}
	public void setParent(ProductCategoryEntity parent) {
		this.parent = parent;
	}
	
	

}
