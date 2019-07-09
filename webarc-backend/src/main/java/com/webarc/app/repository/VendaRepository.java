package com.webarc.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webarc.app.model.User;
import com.webarc.app.model.Sale;

/**
 * DAO for {@link User}.
 */
@Repository
public interface VendaRepository extends PagingAndSortingRepository<Sale, Long> {
    
}
