package com.undcon.app.rest.apis;

import java.util.List;

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

import com.undcon.app.dtos.AmountTotalDto;
import com.undcon.app.dtos.PurchaseExpenseRequestDto;
import com.undcon.app.dtos.PurchaseExpenseResponseDto;
import com.undcon.app.dtos.PurchaseItemDto;
import com.undcon.app.dtos.PurchaseRequestDto;
import com.undcon.app.dtos.PurchaseSimpleDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.services.PurchaseService;

/**
 * Api de Compras
 */
@Component
@Path("/purchases")
public class PurchaseApi {

	@Autowired
	private PurchaseService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<PurchaseEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity get(@PathParam("id") long id) throws UndconException {
		PurchaseEntity Provider = service.findById(id);
		return Provider;
	}
	
	@GET
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<PurchaseItemDto> getitens(@PathParam("id") long id, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) throws UndconException {
		Page<PurchaseItemDto> itens = service.getItens(id, page, size);
		return itens;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity post(PurchaseRequestDto purchaseDto) throws UndconException {
		Assert.notNull(purchaseDto.getProvider(), "provider is required");
		Assert.notNull(purchaseDto.getProvider().getId(), "provider.id is required");
		return service.persist(purchaseDto);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseEntity put(PurchaseRequestDto purchaseDto) throws UndconException {
		Assert.notNull(purchaseDto.getProvider(), "provider is required");
		Assert.notNull(purchaseDto.getProvider().getId(), "provider.id is required");
		return service.update(purchaseDto);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
	
	@GET
	@Path("/{id}/total")
	@Produces(MediaType.APPLICATION_JSON)
	public AmountTotalDto getSaleTotal(@PathParam("id") long id) throws UndconException {
		return service.getPurchaseTotal(id);
	}
	
	@POST
	@Path("/{id}/finalize")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseSimpleDto finalize(@PathParam("id") long id) throws UndconException {
		Assert.isTrue(id > 0, "id element is required");
		return service.finalize(id);
	}
	
	@POST
	@Path("/{id}/toBillList")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseExpenseResponseDto toBillPurchase(@PathParam("id") long id, List<PurchaseExpenseRequestDto> purchaseExpenseDtoList)
			throws UndconException {
		Assert.notNull(purchaseExpenseDtoList, "purchaseExpenseDtoList is required");
		Assert.isTrue(purchaseExpenseDtoList.size() > 0, "one element is required");
		return service.toBill(id, purchaseExpenseDtoList);
	}

	@POST
	@Path("/{id}/toBill")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseExpenseResponseDto toBillSale(@PathParam("id") long id, PurchaseExpenseRequestDto purchaseExpenseDto)
			throws UndconException {
		Assert.notNull(purchaseExpenseDto.getPaymentType(), "paymentType is required");
		Assert.notNull(purchaseExpenseDto.getValue(), "value is required");
		Assert.isTrue(purchaseExpenseDto.getValue() > 0, "value is invalid");
		return service.toBill(id, purchaseExpenseDto);
	}

	@POST
	@Path("/{id}/toCancel")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchaseSimpleDto toCancel(@PathParam("id") long id) throws UndconException {
		return service.toCancel(id);
	}
}
