package com.undcon.app.old.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.undcon.app.model.old.ItemVenda;

/**
 * DAO for {@link User}.
 */
@Repository
public interface ItemVendaRepository extends PagingAndSortingRepository<ItemVenda, Long> {
    
}