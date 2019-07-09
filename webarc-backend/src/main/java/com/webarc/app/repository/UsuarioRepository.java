package com.webarc.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webarc.app.model.User;

/**
 * DAO for {@link User}.
 */
@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findAllByEmail(String email, Pageable pageable);
    
}
