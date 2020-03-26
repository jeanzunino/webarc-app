package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.PurchaseEntity;

public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

}
