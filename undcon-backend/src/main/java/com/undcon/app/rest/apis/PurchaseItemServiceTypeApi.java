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
import com.undcon.app.model.PurchaseItemEntity;
import com.undcon.app.services.PurchaseItemServiceTypeService;

@Component
@Path("/purchases")
public class PurchaseItemServiceTypeApi {

	@Autowired
	private PurchaseItemServiceTypeService itemServiceTypeService;

	@POST
	@Path("/{id}/itensServices")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseItemEntity postItemService(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		Assert.notNull(item.getItemId(), "itemId is required");
		Assert.notNull(item.getQuantity(), "quantity is required");
		return itemServiceTypeService.addItemService(id, item);
	}

	@PUT
	@Path("/{id}/itensServices")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseItemEntity putServiceItem(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		return itemServiceTypeService.updateServiceItem(id, item);
	}

	@DELETE
	@Path("/{saleId}/itensServices/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteServiceItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId)
			throws UndconException {
		itemServiceTypeService.deleteServiceItem(saleId, itemId);
	}
}
