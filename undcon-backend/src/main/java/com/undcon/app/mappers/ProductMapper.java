package com.undcon.app.mappers;

import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ProductDto;
import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.model.ProductEntity;

@Component
public class ProductMapper {

	public ProductEntity toEntity(ProductDto dto) {
		Long id = dto.getId();
		String name= dto.getName();
		String unit= dto.getUnit();
		double purchasePrice = dto.getPurchasePrice();
		double salePrice = dto.getSalePrice();
		long stock = dto.getStock();
		long stockMin = dto.getStockMin();
		ProductCategoryEntity productCategory = null;
		if (dto.getProductCategory() != null) {
			productCategory = new ProductCategoryEntity();
			productCategory.setId(dto.getProductCategory().getId());
		}
		return new ProductEntity(id, name, unit, dto.getGtin(), purchasePrice, salePrice, stock, stockMin, productCategory);
	}
}
