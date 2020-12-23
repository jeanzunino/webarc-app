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

import com.undcon.app.dtos.OpenPdvDto;
import com.undcon.app.dtos.PdvResume;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.mappers.PdvMapper;
import com.undcon.app.model.PdvEntity;
import com.undcon.app.services.PdvService;

@Component
@Path("/pdvs")
public class PdvApi {

	@Autowired
	private PdvService pdvService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<PdvEntity> getAll(@QueryParam("name") String name, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return pdvService.getAll(name, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PdvEntity get(@PathParam("id") long id) {
		PdvEntity entity = pdvService.findById(id);
		return entity;
	}
	
	@GET
	@Path("/resume")
	@Produces(MediaType.APPLICATION_JSON)
	public PdvResume getResume() throws UndconException {
		return pdvService.getResumePdv();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PdvEntity post(OpenPdvDto pdv) throws UndconException {
		return pdvService.persist(pdv);
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PdvEntity put(OpenPdvDto pdv) throws UndconException {
		return pdvService.update(pdv);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws UndconException {
		pdvService.delete(id);
	}
	
}
