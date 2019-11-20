package com.undcon.app.old.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.undcon.app.model.old.Permission;

/**
 * DAO for {@link User}.
 */
@Repository
public interface SessaoRepository extends PagingAndSortingRepository<Permission, Long> {
    
}
