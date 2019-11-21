package com.undcon.app.old.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.undcon.app.model.ProductEntity;

/**
 * DAO for {@link User}.
 */
@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<ProductEntity, Long> {
    
}
