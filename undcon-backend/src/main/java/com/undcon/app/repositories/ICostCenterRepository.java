package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.CostCenterEntity;
import com.undcon.app.model.CustomerEntity;

public interface ICostCenterRepository extends JpaRepository<CostCenterEntity, Long> , QueryDslPredicateExecutor<CostCenterEntity>{
	
	public Page<CostCenterEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

	public List<CostCenterEntity> findByIdNotAndName(Long id, String name);
}
