package com.undcon.app.rest.apis;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ProductItemRequestDto;
import com.undcon.app.dtos.SaleRequestDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.SaleService;

@Component
@Path("/sales")
public class SaleApi {

	@Autowired
	private SaleService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SaleEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return service.getAll(page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity get(@PathParam("id") long id) {
		SaleEntity Provider = service.findById(id);
		return Provider;
	}

	@POST
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity postItem(@PathParam("id") long id, ProductItemRequestDto item) {
		try {
			return service.addItem(id, item);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity putItem(@PathParam("id") long id, ProductItemRequestDto item) {
		try {
			return service.updateItem(id, item);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}/itensProducts/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId) {
		try {
			service.deleteItem(saleId, itemId);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity post(SaleRequestDto sale) {
		try {
			return service.persist(sale);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity put(SaleRequestDto sale) {
		try {
			return service.update(sale);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		try {
			service.delete(id);
		} catch (UndconException e) {
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessageModel(e.getError())).build());
		}
	}
}
