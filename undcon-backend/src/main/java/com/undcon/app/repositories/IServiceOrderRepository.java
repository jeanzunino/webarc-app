package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.ConfigurationEntity;
import com.undcon.app.model.ServiceOrderEntity;

public interface IServiceOrderRepository extends JpaRepository<ServiceOrderEntity, Long>, QueryDslPredicateExecutor<ServiceOrderEntity> {
}
