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

import com.undcon.app.dtos.AmountTotalDto;
import com.undcon.app.dtos.SaleIncomeRequestDto;
import com.undcon.app.dtos.SaleIncomeResponseDto;
import com.undcon.app.dtos.SaleItemDto;
import com.undcon.app.dtos.SaleRequestDto;
import com.undcon.app.dtos.SaleSimpleDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.SaleEntity;
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
	@Path("/{id}/total")
	@Produces(MediaType.APPLICATION_JSON)
	public AmountTotalDto getSaleTotal(@PathParam("id") long id) throws UndconException {
		return service.getSaleTotal(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity post(SaleRequestDto sale) throws UndconException {
		Assert.notNull(sale.getCustomer(), "customer is required");

		Assert.notNull(sale.getCustomer().getId(), "customer.id is required");
		return service.persist(sale);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleEntity put(SaleRequestDto sale) throws UndconException {
		Assert.notNull(sale.getCustomer(), "customer is required");

		Assert.notNull(sale.getCustomer().getId(), "customer.id is required");
		return service.update(sale);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}

	@POST
	@Path("/{id}/finalize")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleSimpleDto finalize(@PathParam("id") long id) throws UndconException {
		Assert.isTrue(id > 0, "id element is required");
		return service.finalize(id);
	}
	
	@POST
	@Path("/{id}/toBillList")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleIncomeResponseDto toBillSale(@PathParam("id") long id, List<SaleIncomeRequestDto> saleIncomeDtoList)
			throws UndconException {
		Assert.notNull(saleIncomeDtoList, "saleIncomeDtoList is required");
		Assert.isTrue(saleIncomeDtoList.size() > 0, "one element is required");
		return service.toBill(id, saleIncomeDtoList);
	}

	@POST
	@Path("/{id}/toBill")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleIncomeResponseDto toBillSale(@PathParam("id") long id, SaleIncomeRequestDto saleIncomeDto)
			throws UndconException {
		Assert.notNull(saleIncomeDto.getPaymentType(), "paymentType is required");
		Assert.notNull(saleIncomeDto.getValue(), "value is required");
		Assert.isTrue(saleIncomeDto.getValue() > 0, "value is invalid");
		return service.toBill(id, saleIncomeDto);
	}

	@POST
	@Path("/{id}/toCancel")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleSimpleDto toCancel(@PathParam("id") long id) throws UndconException {
		return service.toCancel(id);
	}

}
