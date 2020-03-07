package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.TenantEntity;

public interface ITenantRepository extends JpaRepository<TenantEntity, Long> {
}
