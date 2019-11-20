package com.undcon.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.undcon.app.model.UserEntity;

public interface IUserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	Page<UserEntity> findAllByLogin(String login, Pageable pageable);
}
