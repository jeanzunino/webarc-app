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

import com.undcon.app.dtos.IncomeDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.IncomeMapper;
import com.undcon.app.model.IncomeEntity;
import com.undcon.app.services.IncomeService;
import com.undcon.app.utils.PageUtils;

/**
 * Api de Receitas
 */
@Component
@Path("/incomes")
public class IncomeApi {

	@Autowired
	private IncomeMapper mapper;

	@Autowired
	private IncomeService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<IncomeDto> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		Page<IncomeEntity> all = service.getAll(filter, page, size);
		List<IncomeDto> content = mapper.toDto(all.getContent());
		Page<IncomeDto> pageDto = new PageImpl<IncomeDto>(content, PageUtils.createPageRequest(page, size),
				all.getTotalElements());
		return pageDto;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public IncomeDto get(@PathParam("id") long id) throws UndconException {
		IncomeEntity customer = service.findById(id);
		return mapper.toDto(customer);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public IncomeDto post(IncomeEntity customer) throws UndconException {
		return mapper.toDto(service.persist(customer));
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public IncomeDto put(IncomeEntity customer) throws UndconException {
		return mapper.toDto(service.update(customer));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
