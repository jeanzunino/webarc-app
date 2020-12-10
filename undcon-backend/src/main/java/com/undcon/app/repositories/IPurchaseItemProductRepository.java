package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.PurchaseItemProductEntity;

public interface IPurchaseItemProductRepository
		extends JpaRepository<PurchaseItemProductEntity, Long>, QueryDslPredicateExecutor<PurchaseItemProductEntity> {

	public boolean existsByPurchase(PurchaseEntity purchase);
}
