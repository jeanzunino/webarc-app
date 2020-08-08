package com.undcon.app.rest.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.model.BankCheckEntity;
import com.undcon.app.services.BankCheckService;

/**
 * Api de Clientes
 */
@Component
@Path("/checks")
public class BankCheckApi {

	@Autowired
	private BankCheckService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<BankCheckEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

}
