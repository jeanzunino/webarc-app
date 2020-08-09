package com.undcon.app.rest.apis;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.exceptions.UndconException;
import com.undcon.app.services.SaleItemProductService;

@Component
@Path("/sales")
public class SaleItemProductApi {

	@Autowired
	private SaleItemProductService itemProductService;
	
	@DELETE
	@Path("/{saleId}/itensProducts/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProductItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId)
			throws UndconException {
		itemProductService.deleteProductItem(saleId, itemId);
	}
}
