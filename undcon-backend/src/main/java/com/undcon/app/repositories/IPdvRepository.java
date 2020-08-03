package com.undcon.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.PdvEntity;
import com.undcon.app.model.UserEntity;

public interface IPdvRepository extends JpaRepository<PdvEntity, Long> {
	
	public List<PdvEntity> findByUser(UserEntity user);
}
