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
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.undcon.app.dtos.ExpenseDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.ExpenseMapper;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.services.ExpenseService;
import com.undcon.app.utils.PageUtils;

/**
 * Api de Despesas
 */
@Component
@Path("/expenses")
public class ExpenseApi {

	@Autowired
	private ExpenseService service;

	@Autowired
	private ExpenseMapper mapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ExpenseDto> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		Page<ExpenseEntity> all = service.getAll(filter, page, size);
		List<ExpenseDto> content = mapper.toDto(all.getContent());
		Page<ExpenseDto> pageDto = new PageImpl<ExpenseDto>(content, PageUtils.createPageRequest(page, size),
				all.getTotalElements());
		return pageDto;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseDto get(@PathParam("id") long id) throws UndconException {
		ExpenseEntity customer = service.findById(id);
		return mapper.toDto(customer);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseDto post(ExpenseEntity expense) throws UndconException {
		Assert.notNull(expense.getProvider(), "provider is required");
		Assert.notNull(expense.getProvider().getId(), "provider.id is required");
		Assert.notNull(expense.getPaymentType(), "paymentType is required");
		Assert.notNull(expense.getPaymentStatus(), "paymentStatus is required");
		Assert.isTrue(expense.getValue() > 0, "value is invalid");
		return mapper.toDto(service.persist(expense));
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExpenseDto put(ExpenseEntity expense) throws UndconException {
		Assert.notNull(expense.getProvider(), "provider is required");
		Assert.notNull(expense.getProvider().getId(), "provider.id is required");
		Assert.notNull(expense.getPaymentType(), "paymentType is required");
		Assert.notNull(expense.getPaymentStatus(), "paymentStatus is required");
		Assert.isTrue(expense.getValue() > 0, "value is invalid");
		return mapper.toDto(service.update(expense));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
