package com.undcon.app.dtos;

public class ProductCategoryDto {

	private Long id;
	private String name;
	private ProductCategoryDto parent;

	public ProductCategoryDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductCategoryDto(Long id, String name, ProductCategoryDto parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategoryDto getParent() {
		return parent;
	}

	public void setParent(ProductCategoryDto parent) {
		this.parent = parent;
	}

}
