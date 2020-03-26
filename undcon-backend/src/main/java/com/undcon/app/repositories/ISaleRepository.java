package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.SaleEntity;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
	
}
