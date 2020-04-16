package com.undcon.app.services;

import org.springframework.stereotype.Component;

import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductEntity;

@Component
public class StockService {

	public void checkStockAvaiable(ProductEntity product, long quantity) throws UndconException {
		if(getStockAvaiableFromProduct(product) < quantity) {
			throw new UndconException(UndconError.SALE_ITEM_STOCK_NOT_AVAIABLE);
		}
	}
	
	public void discounProductOfStock(ProductEntity product, long quantity) throws UndconException {
		product.setStock(getStockAvaiableFromProduct(product) - quantity);
	}
	
	public void addProductInStock(ProductEntity product, long quantity) throws UndconException {
		product.setStock(getStockAvaiableFromProduct(product) + quantity);
	}
	
	public long getStockAvaiableFromProduct(ProductEntity product) {
		return product.getStock();
	}
}
