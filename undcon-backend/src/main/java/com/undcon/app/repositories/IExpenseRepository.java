package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ExpenseEntity;

public interface IExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
