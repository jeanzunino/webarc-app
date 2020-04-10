package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.UserEntity;


public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByLogin(String login);
	
	List<UserEntity> findByEmployee(EmployeeEntity employee);
	
	Page<UserEntity> findAllByLogin(String login, Pageable pageable);

	UserEntity findAllByLoginAndPassword(String login, String password);
}
