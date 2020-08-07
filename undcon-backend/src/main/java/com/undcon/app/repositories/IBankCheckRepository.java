package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.BankCheckEntity;

public interface IBankCheckRepository
		extends JpaRepository<BankCheckEntity, Long>, QueryDslPredicateExecutor<BankCheckEntity> {

}
