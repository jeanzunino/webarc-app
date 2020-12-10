package com.undcon.app.multitenancy;

import java.util.Map;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DataSourceConfiguration  implements ApplicationRunner {

	private final DataSourceProperties dataSourceProperties;

	public DataSourceConfiguration(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

	public void migrate() {
		Map<String, DataSource> datasources = dataSourceProperties.getDatasources();
		migrate("public", datasources.get("public"), DatabaseSchemaType.PUBLIC);
		
		System.err.println("Migrando tenants " + datasources.size());
		datasources.keySet().stream()
				.forEach(tenant -> migrate(tenant, datasources.get(tenant), DatabaseSchemaType.TENANTS));
	}

	public void migrate(String tenant, DataSource dataSource, DatabaseSchemaType schemaType) {
		if (schemaType == DatabaseSchemaType.TENANTS && tenant.equals("public")) {
			return;
		}
		System.err.println("Migrando schema " + tenant);
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setLocations(schemaType.getLocation());
		flyway.setBaselineOnMigrate(false);
		flyway.setOutOfOrder(true);
		flyway.migrate();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		migrate();		
	}

}
