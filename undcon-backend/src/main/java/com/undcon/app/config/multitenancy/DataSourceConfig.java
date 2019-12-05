package com.undcon.app.config.multitenancy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.undcon.app.model.TenantEntity;
import com.undcon.app.services.TenantService;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DataSourceConfig {

	@Autowired
	private TenantService tenantService;

	@Bean
	public DataSource dataSource() {
		return configureDataSource();
	}

	public AbstractRoutingDataSource configureDataSource() {
		AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

		Map<Object, Object> targetDataSources = new HashMap<>();

		targetDataSources.put("public", createDataSource("public"));

		List<TenantEntity> all = tenantService.getAll();

//		for (TenantEntity tenantEntity : all) {
//			String schemaName = tenantEntity.getSchemaName();
//			targetDataSources.put(schemaName, createDataSource(schemaName));
//		}
//
//		dataSource.setTargetDataSources(targetDataSources);
//
//		dataSource.afterPropertiesSet();

		return dataSource;
	}

	public DataSource createDataSource(String schema) {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setInitializationFailTimeout(0);
		dataSource.setMaximumPoolSize(5);
		dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		dataSource.addDataSourceProperty("url", "jdbc:postgresql://127.0.0.1:5432/db");
		dataSource.setSchema(schema);
		dataSource.addDataSourceProperty("user", "postgres");
		dataSource.addDataSourceProperty("password", "198706");
		ThreadLocalStorage.setTenantName(schema);
		return dataSource;
	}

}
