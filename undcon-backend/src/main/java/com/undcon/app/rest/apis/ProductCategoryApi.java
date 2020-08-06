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

import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.services.ProductCategoryService;

/**
 * Api de Categoria de Produtos
 */
@Component
@Path("/productCategories")
public class ProductCategoryApi {

	@Autowired
	private ProductCategoryService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ProductCategoryEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		
		//TODO BUG - Não está retornando a categoria PAI, mesmo colocando o Pai como EAGER.
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity get(@PathParam("id") long id) throws UndconException {
		ProductCategoryEntity entity = service.findById(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity post(ProductCategoryEntity entity) throws UndconException {
		return service.persist(entity);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity put(ProductCategoryEntity entity) throws UndconException {
		return service.update(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		service.delete(id);
	}
}
