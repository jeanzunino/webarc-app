package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.IncomeEntity;
import com.undcon.app.model.SaleEntity;

public interface IIncomeRepository extends JpaRepository<IncomeEntity, Long>, QueryDslPredicateExecutor<IncomeEntity> {

	public List<IncomeEntity> findBySale(SaleEntity sale);
}
