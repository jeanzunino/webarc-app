package com.undcon.app.multitenancy;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DataSourceBeanApplicationListener implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	private  DataSourceProperties dataSourceProperties;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		TenantAwareRoutingSource dataSource = event.getApplicationContext().getBean(TenantAwareRoutingSource.class);

		Map<Object, Object> targetDataSources = new HashMap<>();
		Map<String, DataSource> datasources = dataSourceProperties.getDatasources();
		datasources.keySet().forEach(tenant -> targetDataSources.put(tenant, datasources.get(tenant)));

		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(DataSourceProperties.createDataSource(null));
		dataSource.afterPropertiesSet();
	}
	
}
