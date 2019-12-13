package com.undcon.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	Page<UserEntity> findAllByLogin(String login, Pageable pageable);

	UserEntity findAllByLoginAndPassword(String login, String password);
}
