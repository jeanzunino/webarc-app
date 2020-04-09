package com.undcon.app.multitenancy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
@Order(1)
@ConfigurationProperties(prefix = "tenants")
public class DataSourceProperties implements ApplicationRunner {

	private Map<String, DataSource> datasources = new LinkedHashMap<>();

	private List<String> tenants = new ArrayList<String>();

	public DataSourceProperties() {
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		setDatasources();
	}

	public void setTenants(List<String> tenants) {
		this.tenants = tenants;
	}

	public Map<String, DataSource> getDatasources() {
		return datasources;
	}
	
	public AbstractRoutingDataSource configureDataSource() {
		System.err.println("Configurando Datasources");
		AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

		Map<Object, Object> targetDataSources = new HashMap<>();

		datasources.keySet().forEach(tenant -> targetDataSources.put(tenant, datasources.get(tenant)));

		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(createDataSource(null));
		dataSource.afterPropertiesSet();

		return dataSource;
	}

	public void setDatasources() {
		datasources.put("public", createDataSource("public"));
		tenants.stream().forEach(tenant -> datasources.put(tenant, createDataSource(tenant)));
	}

	public static DataSource createDataSource(String schema) {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setInitializationFailTimeout(0);
		dataSource.setMaximumPoolSize(5);
		dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		dataSource.addDataSourceProperty("url", "jdbc:postgresql://127.0.0.1:5432/postgres");
		dataSource.setSchema(schema);
		dataSource.addDataSourceProperty("user", "postgres");
		dataSource.addDataSourceProperty("password", "postgres");
		ThreadLocalStorage.setTenantName(schema);
		return dataSource;
	}

}
