package com.undcon.app.rest.apis;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ProductItemRequestDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.PurchaseItemEntity;
import com.undcon.app.services.PurchaseService;

@Component
@Path("/purchases")
public class PurchaseApi {

	@Autowired
	private PurchaseService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<PurchaseEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return service.getAll(page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity get(@PathParam("id") long id) {
		PurchaseEntity Provider = service.findById(id);
		return Provider;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity post(PurchaseEntity Provider) throws UndconException {
		return service.persist(Provider);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity put(PurchaseEntity provider) throws UndconException {
		return service.update(provider);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}

	@POST
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseItemEntity postItem(@PathParam("id") long id, ProductItemRequestDto item) throws UndconException {
		return service.addItem(id, item);
	}

	@PUT
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseItemEntity putItem(@PathParam("id") long id, ProductItemRequestDto item) throws UndconException {
			return service.updateItem(id, item);
	}

	@DELETE
	@Path("/{id}/itensProducts/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId) throws UndconException {
			service.deleteItem(saleId, itemId);
	}
}
