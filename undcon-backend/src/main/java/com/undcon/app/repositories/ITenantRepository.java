package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.TenantEntity;

public interface ITenantRepository extends PagingAndSortingRepository<TenantEntity, Long> {
}