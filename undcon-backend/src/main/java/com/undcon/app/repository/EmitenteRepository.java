package com.undcon.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.undcon.app.model.old.Emitente;

/**
 * DAO for {@link User}.
 */
@Repository
public interface EmitenteRepository extends PagingAndSortingRepository<Emitente, Long> {
    
}