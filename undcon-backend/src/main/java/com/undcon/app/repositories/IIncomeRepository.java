package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.IncomeEntity;

public interface IIncomeRepository extends JpaRepository<IncomeEntity, Long> {
}
