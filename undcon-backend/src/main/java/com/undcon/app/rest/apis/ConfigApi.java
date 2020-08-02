package com.undcon.app.rest.apis;

import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.model.ConfigurationEntity;
import com.undcon.app.repositories.IConfigurationRepository;

/**
 * Api de Configurações
 */
@Component
@Path("/configuration")
public class ConfigApi {

	@Autowired
	private IConfigurationRepository repository;

	@POST
	@Path("/logo")
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM, "image/png" })
	@Produces(MediaType.TEXT_PLAIN)
	public Response postImageFile(byte[] file) {

		ConfigurationEntity config = null;
		Iterator<ConfigurationEntity> iterator = repository.findAll().iterator();
		if (iterator.hasNext()) {
			config = iterator.next();
			config.setLogo(file);
		} else {
			config = new ConfigurationEntity(null, file);
		}
		repository.save(config);
		return Response.ok().build();
	}

	@GET
	@Path("/logo")
	@Produces({ MediaType.APPLICATION_OCTET_STREAM, "image/png" })
	public Response getLogo() {

		ConfigurationEntity config = null;
		Iterator<ConfigurationEntity> iterator = repository.findAll().iterator();
		if (iterator.hasNext()) {
			config = iterator.next();
			return Response.ok(config.getLogo(), MediaType.APPLICATION_OCTET_STREAM).build();
		}
		throw new WebApplicationException("Configuração não encontrada", Response.Status.NOT_FOUND);
	}
}
