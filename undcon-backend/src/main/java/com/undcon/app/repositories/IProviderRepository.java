package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.ProviderEntity;

public interface IProviderRepository
		extends JpaRepository<ProviderEntity, Long>, QueryDslPredicateExecutor<ProviderEntity> {

	public Page<ProviderEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

	public List<ProviderEntity> findByIdNotAndNameIgnoreCase(Long id, String name);
}
