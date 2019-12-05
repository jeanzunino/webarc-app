package com.undcon.app.rest.apis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.repositories.IProductCategoryRepository;

@Component
@Path("/productCategories")
public class ProductCategoryApi {

	@Autowired
	private IProductCategoryRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductCategoryEntity> getAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity get(@PathParam("id") long id) {
		ProductCategoryEntity entity = repository.findOne(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity post(ProductCategoryEntity entity) {
		return repository.save(entity);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryEntity put(ProductCategoryEntity entity) {
		return repository.save(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}
