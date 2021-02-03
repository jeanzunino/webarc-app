package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long>, QueryDslPredicateExecutor<UserEntity> {

	List<UserEntity> findByLogin(String login);

	List<UserEntity> findByEmployee(EmployeeEntity employee);

	Page<UserEntity> findAllByLoginContainingIgnoreCase(String login, Pageable pageable);

	UserEntity findAllByLoginAndPassword(String login, String password);

	UserEntity findByLoginAndTokenResetarSenha(String user, String token);
}
