package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.IncomeEntity;

public interface IIncomeRepository extends PagingAndSortingRepository<IncomeEntity, Long> {
}
