package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.PdvEntity;

public interface IPdvRepository extends JpaRepository<PdvEntity, Long> {
}
