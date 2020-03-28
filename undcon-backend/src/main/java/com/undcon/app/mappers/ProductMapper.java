package com.undcon.app.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ProductSimpleDto;
import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.repositories.IProductCategoryRepository;

@Component
public class ProductMapper {

	@Autowired
	private IProductCategoryRepository productCategoryRepository;

	public ProductEntity toEntity(ProductSimpleDto dto) {
		Long id = dto.getId();
		String name= dto.getName();
		String unit= dto.getUnit();
		double purchasePrice = 0;
		double salePrice = 0;
		long stock = 0;
		long stockMin = 0;
		ProductCategoryEntity productCategory = null;
		if (dto.getProductCategory() != null) {
			productCategory = productCategoryRepository.findOne(dto.getProductCategory().getId());
		}
		return new ProductEntity(id, name, unit, purchasePrice, salePrice, stock, stockMin, productCategory);
	}
}
