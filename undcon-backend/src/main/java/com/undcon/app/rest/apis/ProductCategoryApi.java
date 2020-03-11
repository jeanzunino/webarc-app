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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
import com.undcon.app.services.ProductCategoryService;

@Component
@Path("/productCategories")
public class ProductCategoryApi {

	@Autowired
	private ProductCategoryService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductCategoryEntity> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
		return service.getAll(page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity get(@PathParam("id") long id) {
		ProductCategoryEntity entity = service.findById(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity post(ProductCategoryEntity entity) {
		try {
			return service.persist(entity);
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity put(ProductCategoryEntity entity) {
		try {
			return service.update(entity);
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		service.delete(id);
	}
}
