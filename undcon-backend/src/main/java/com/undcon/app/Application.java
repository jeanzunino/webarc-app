package com.undcon.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.undcon.app.multitenancy.TenantAwareRoutingSource;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

	public static void main(String... args) {
		new Application()//
				.configure(new SpringApplicationBuilder(Application.class))//
				//
				.run(args);
	}
	
	@Bean
	public DataSource dataSource() {
		return configureDataSource();
	}

	private static Properties getDefaultProperties() {

		Properties defaultProperties = new Properties();

		// Set sane Spring Hibernate properties:
		defaultProperties.put("spring.jpa.show-sql", "true");
		defaultProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
		defaultProperties.put("spring.datasource.initialize", "false");

		// Prevent JPA from trying to Auto Detect the Database:
		defaultProperties.put("spring.jpa.database", "postgresql");

		// Prevent Hibernate from Automatic Changes to the DDL Schema:
		defaultProperties.put("spring.jpa.hibernate.ddl-auto", "true");

		return defaultProperties;
	}
	
	public AbstractRoutingDataSource configureDataSource() {
		AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

		Map<Object, Object> targetDataSources = new HashMap<>();

		targetDataSources.put("public", createDataSource("public"));

		targetDataSources.put("cliente1", createDataSource("cliente1"));
		
		dataSource.setTargetDataSources(targetDataSources);

		dataSource.afterPropertiesSet();

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
