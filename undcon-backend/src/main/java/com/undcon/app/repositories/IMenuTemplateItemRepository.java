package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.MenuTemplateEntity;
import com.undcon.app.model.MenuTemplateItemEntity;

public interface IMenuTemplateItemRepository extends PagingAndSortingRepository<MenuTemplateItemEntity, Long> {
	
	public List<MenuTemplateItemEntity> findByMenu(MenuTemplateEntity menu);
}
