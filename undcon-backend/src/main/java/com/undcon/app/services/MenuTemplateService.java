package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.model.MenuTemplateEntity;
import com.undcon.app.repositories.IMenuTemplateRepository;

@Component
public class MenuTemplateService extends AbstractService<MenuTemplateEntity> {

	@Autowired
	private IMenuTemplateRepository menuTemplateRepository;

	public Page<MenuTemplateEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(MenuTemplateEntity.class, filter, page, size);
	}

	@Override
	protected JpaRepository<MenuTemplateEntity, Long> getRepository() {
		return menuTemplateRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.MENU_TEMPLATE;
	}

}
