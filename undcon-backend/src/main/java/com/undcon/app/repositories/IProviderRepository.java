package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ProviderEntity;

public interface IProviderRepository extends JpaRepository<ProviderEntity, Long> {
	
	public List<ProviderEntity> findByIdNotAndName(Long id, String name);
}
