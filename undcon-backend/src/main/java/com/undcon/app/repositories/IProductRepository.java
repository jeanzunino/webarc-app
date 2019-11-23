package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.ProductEntity;

public interface IProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
}
