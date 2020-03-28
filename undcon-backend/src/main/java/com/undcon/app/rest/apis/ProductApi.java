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

import com.undcon.app.dtos.ProductSimpleDto;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.ProductMapper;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.rest.models.ErrorMessageModel;
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
	public List<ProductEntity> getAll(@QueryParam("name") String name, @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
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
	public ProductEntity post(ProductSimpleDto dto) {
		try {
			return productService.persist(productMapper.toEntity(dto));
		} catch (UndconException e) {
			throw new WebApplicationException(Response
				     .status(Response.Status.BAD_REQUEST)
				     .entity(new ErrorMessageModel(e.getError())).build());
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductEntity put(ProductSimpleDto dto) {
		try {
			return productService.update(productMapper.toEntity(dto));
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
		productService.delete(id);
	}
}
