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

import com.undcon.app.dtos.ProductSimpleDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.ProductMapper;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.services.ProductService;

@Component
@Path("/products")
public class ProductApi {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<ProductEntity> getAll(@QueryParam("name") String name, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return productService.getAll(name, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity get(@PathParam("id") long id) {
		ProductEntity entity = productService.findById(id);
		return entity;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity post(ProductSimpleDto dto) throws UndconException {
		return productService.persist(productMapper.toEntity(dto));
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity put(ProductSimpleDto dto) throws UndconException {
		return productService.update(productMapper.toEntity(dto));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		productService.delete(id);
	}
	
	@GET
	@Path("/stockMin")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductEntity> get() {
		return productService.getStockMin();
	}
}
