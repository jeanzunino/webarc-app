package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.MenuTemplateEntity;

public interface IMenuTemplateRepository extends JpaRepository<MenuTemplateEntity, Long> {
}
