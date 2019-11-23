package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.ProviderEntity;

public interface IProviderRepository extends PagingAndSortingRepository<ProviderEntity, Long> {
}
