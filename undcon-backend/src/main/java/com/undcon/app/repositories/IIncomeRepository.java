package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.IncomeEntity;

public interface IIncomeRepository extends JpaRepository<IncomeEntity, Long>, QueryDslPredicateExecutor<IncomeEntity> {
}
