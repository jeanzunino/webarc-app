package com.undcon.app.multitenancy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.undcon.app.repositories.TenantRepositoryImpl;

@Component
@Order(0)
public class DataSourceLoadTenants implements ApplicationRunner{

	@Autowired
	private TenantRepositoryImpl tenantRepository;
	
	@Autowired
	private  DataSourceProperties dataSourceProperties;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadTenants();
	}
	
	public void loadTenants() {
		System.err.println("Carregando tenants...");
		List<String> tenants = new ArrayList<String>();
		tenantRepository.getAll().stream().forEach(schemaName -> tenants.add(schemaName));
		dataSourceProperties.setTenants(tenants);
	}

}
