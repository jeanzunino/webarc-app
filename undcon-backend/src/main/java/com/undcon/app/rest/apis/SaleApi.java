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
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.dtos.ProductSaledInfoDto;
import com.undcon.app.dtos.SaleInfoDto;
import com.undcon.app.dtos.SaleItemDto;
import com.undcon.app.dtos.SaleRequestDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.services.SaleService;

/**
 * Api de Vendas
 */
@Component
@Path("/sales")
public class SaleApi {

	@Autowired
	private SaleService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<SaleEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity get(@PathParam("id") long id) throws UndconException {
		SaleEntity Provider = service.findById(id);
		return Provider;
	}

	@GET
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<SaleItemDto> getitens(@PathParam("id") long id, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) throws UndconException {
		Page<SaleItemDto> itens = service.getItens(id, page, size);
		return itens;
	}

	@GET
	@Path("/total")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleInfoDto getTotal() {
		return service.getTotalSale();
	}

	@GET
	@Path("/topProductSaled")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductSaledInfoDto> getTopProductSaled() {
		return service.getTopProductSaled(true);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity post(SaleRequestDto sale) throws UndconException {
		Assert.notNull(sale.getCustomer(), "customer is required");
		Assert.notNull(sale.getSalesman(), "salesman is required");

		Assert.notNull(sale.getCustomer().getId(), "customer.id is required");
		Assert.notNull(sale.getSalesman().getId(), "salesman.id is required");
		return service.persist(sale);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity put(SaleRequestDto sale) throws UndconException {
		return service.update(sale);
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
	public SaleItemEntity postItemProduct(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		Assert.notNull(item.getEmployeeId(), "employeeId is required");
		Assert.notNull(item.getItemId(), "itemId is required");
		Assert.notNull(item.getQuantity(), "quantity is required");
		return service.addItemProduct(id, item);
	}

	@POST
	@Path("/{id}/itensServices")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity postItemService(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		Assert.notNull(item.getEmployeeId(), "employeeId is required");
		Assert.notNull(item.getItemId(), "itemId is required");
		Assert.notNull(item.getQuantity(), "quantity is required");
		return service.addItemService(id, item);
	}

	@PUT
	@Path("/{id}/itensProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity putProductItem(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		return service.updateProductItem(id, item);
	}

	@PUT
	@Path("/{id}/itensServices")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleItemEntity putServiceItem(@PathParam("id") long id, ItemRequestDto item) throws UndconException {
		return service.updateServiceItem(id, item);
	}

	@DELETE
	@Path("/{saleId}/itensProducts/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteProductItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId)
			throws UndconException {
		service.deleteProductItem(saleId, itemId);
	}

	@DELETE
	@Path("/{saleId}/itensServices/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteServiceItem(@PathParam("saleId") long saleId, @PathParam("itemId") long itemId)
			throws UndconException {
		service.deleteServiceItem(saleId, itemId);
	}
}
