package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.ConfigurationEntity;

public interface IConfigurationRepository extends JpaRepository<ConfigurationEntity, Long>, QueryDslPredicateExecutor<ConfigurationEntity> {
}
