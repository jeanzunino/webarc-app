package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.PurchaseEntity;

public interface IPurchaseRepository
		extends JpaRepository<PurchaseEntity, Long>, QueryDslPredicateExecutor<PurchaseEntity> {

}
