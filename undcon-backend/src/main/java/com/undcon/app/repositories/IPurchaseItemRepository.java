package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.PurchaseItemEntity;

public interface IPurchaseItemRepository
		extends JpaRepository<PurchaseItemEntity, Long>, QueryDslPredicateExecutor<PurchaseItemEntity> {

}
