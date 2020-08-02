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

import com.undcon.app.model.MenuTemplateEntity;
import com.undcon.app.model.MenuTemplateItemEntity;
import com.undcon.app.repositories.IMenuTemplateItemRepository;
import com.undcon.app.repositories.IMenuTemplateRepository;
import com.undcon.app.services.MenuTemplateService;

/**
 * Api de Configuração do Menu
 */
@Component
@Path("/menus")
public class MenuTemplateApi {

	@Autowired
	private MenuTemplateService service;
	
	@Autowired
	private IMenuTemplateRepository menuTemplateRepository;

	@Autowired
	private IMenuTemplateItemRepository menuTemplateItemRepository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<MenuTemplateEntity> getAll(@QueryParam("filter") String filter, @QueryParam("page") Integer page,
			@QueryParam("size") Integer size) {
		return service.getAll(filter, page, size);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuTemplateEntity get(@PathParam("id") long id) {
		MenuTemplateEntity entity = menuTemplateRepository.findOne(id);
		return entity;
	}

	@GET
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MenuTemplateItemEntity> getItens(@PathParam("id") long id) {
		MenuTemplateEntity menuntity = menuTemplateRepository.findOne(id);
		List<MenuTemplateItemEntity> items = menuTemplateItemRepository.findByMenu(menuntity);
		return items;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public MenuTemplateEntity post(MenuTemplateEntity entity) {
		return menuTemplateRepository.save(entity);
	}

	@POST
	@Path("/{id}/itens")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuTemplateItemEntity postItem(MenuTemplateItemEntity entity) {
		return menuTemplateItemRepository.save(entity);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public MenuTemplateEntity put(MenuTemplateEntity entity) {
		return menuTemplateRepository.save(entity);
	}

	@PUT
	@Path("/{id}/itens/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public MenuTemplateItemEntity putItem(MenuTemplateItemEntity entity) {
		return menuTemplateItemRepository.save(entity);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) {
		menuTemplateRepository.delete(id);
	}

	@DELETE
	@Path("/{id}/itens/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteItem(@PathParam("itemId") long itemId) {
		menuTemplateItemRepository.delete(itemId);
	}
}
