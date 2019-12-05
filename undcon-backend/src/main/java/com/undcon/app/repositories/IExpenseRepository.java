package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.ExpenseEntity;

public interface IExpenseRepository extends PagingAndSortingRepository<ExpenseEntity, Long> {
}
