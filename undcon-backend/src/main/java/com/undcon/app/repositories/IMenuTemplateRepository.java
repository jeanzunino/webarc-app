package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.MenuTemplateEntity;

public interface IMenuTemplateRepository extends PagingAndSortingRepository<MenuTemplateEntity, Long> {
}
