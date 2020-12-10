package com.undcon.app.rest.apis;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.services.SaleItemProductService;

@Component
@Path("/sales")
public class SaleItemProductApi {

	@Autowired
	private SaleItemProductService itemProductService;
	
	@POST
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity postItemProduct(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		Assert.notNull(item.getEmployeeId(), "employeeId is required");
		Assert.notNull(item.getItemId(), "itemId is required");
		Assert.notNull(item.getQuantity(), "quantity is required");
		return itemProductService.addItemProduct(id, item);
	}
	
	@PUT
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity putProductItem(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		return itemProductService.updateProductItem(id, item);
	}
	
	@DELETE
	@Path("/{saleId}/itensProducts/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProductItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId)
			throws UndconException {
		itemProductService.deleteProductItem(saleId, itemId);
	}
}
