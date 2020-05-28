package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.MenuTemplateEntity;

public interface IMenuTemplateRepository
		extends JpaRepository<MenuTemplateEntity, Long>, QueryDslPredicateExecutor<MenuTemplateEntity> {
}
