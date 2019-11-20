package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.ServiceEntity;

public interface IServiceRepository extends PagingAndSortingRepository<ServiceEntity, Long> {
}
