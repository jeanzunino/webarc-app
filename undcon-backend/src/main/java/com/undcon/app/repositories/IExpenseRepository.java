package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.model.PurchaseEntity;

public interface IExpenseRepository extends JpaRepository<ExpenseEntity, Long>, QueryDslPredicateExecutor<ExpenseEntity> {

	public List<ExpenseEntity> findByPurchase(PurchaseEntity purchase);
}
