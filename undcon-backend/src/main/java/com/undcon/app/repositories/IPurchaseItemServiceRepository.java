package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.PurchaseItemServiceEntity;

public interface IPurchaseItemServiceRepository
		extends JpaRepository<PurchaseItemServiceEntity, Long>, QueryDslPredicateExecutor<PurchaseItemServiceEntity> {

	public boolean existsByPurchase(PurchaseEntity purchase);
}
