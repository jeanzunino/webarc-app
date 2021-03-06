package com.undcon.app.dtos;

public class ProductSimpleDto {

	private Long id;
	private String name;
	private String unit;
	private ProductCategoryDto productCategory;

	public ProductSimpleDto() {
	}

	public ProductSimpleDto(Long id, String name, String unit, ProductCategoryDto productCategory) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.productCategory = productCategory;
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

	public ProductCategoryDto getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryDto productCategory) {
		this.productCategory = productCategory;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
