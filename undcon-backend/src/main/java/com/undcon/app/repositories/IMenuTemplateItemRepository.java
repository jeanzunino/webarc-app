package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.MenuTemplateEntity;
import com.undcon.app.model.MenuTemplateItemEntity;

public interface IMenuTemplateItemRepository extends JpaRepository<MenuTemplateItemEntity, Long>, QueryDslPredicateExecutor<MenuTemplateItemEntity> {

	public List<MenuTemplateItemEntity> findByMenu(MenuTemplateEntity menu);
}
