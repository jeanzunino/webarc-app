package com.undcon.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.ProductCategoryEntity;

public interface IProductCategoryRepository extends PagingAndSortingRepository<ProductCategoryEntity, Long> {
}
