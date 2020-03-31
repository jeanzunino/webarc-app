package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ProviderEntity;

public interface IProviderRepository extends JpaRepository<ProviderEntity, Long> {
	
	public Page<ProviderEntity> findAllByName(String name, Pageable pageable);
	
	public List<ProviderEntity> findByIdNotAndName(Long id, String name);
}
