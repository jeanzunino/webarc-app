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

import com.undcon.app.model.ProductEntity;
import com.undcon.app.repositories.IProductRepository;

@Component
@Path("/products")
public class ProductApi {

	@Autowired
	private IProductRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductEntity> getAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity get(@PathParam("id") long id) {
		ProductEntity entity = repository.findOne(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity post(ProductEntity entity) {
		return repository.save(entity);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity put(ProductEntity entity) {
		return repository.save(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		repository.delete(id);
	}
}
