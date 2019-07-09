package com.webarc.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webarc.app.model.Anexo;
import com.webarc.app.model.User;

/**
 * DAO for {@link User}.
 */
@Repository
public interface AnexoRepository extends PagingAndSortingRepository<Anexo, Long> {
    
}
