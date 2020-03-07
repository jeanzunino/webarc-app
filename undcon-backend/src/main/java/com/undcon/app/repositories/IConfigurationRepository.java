package com.undcon.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.undcon.app.model.ConfigurationEntity;

public interface IConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
}
