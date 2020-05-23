package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.SaleEntity;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long>, QueryDslPredicateExecutor<SaleEntity> {

}
