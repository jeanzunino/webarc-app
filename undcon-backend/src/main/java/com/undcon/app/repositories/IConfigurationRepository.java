package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.ConfigurationEntity;

public interface IConfigurationRepository extends PagingAndSortingRepository<ConfigurationEntity, Long> {
}
