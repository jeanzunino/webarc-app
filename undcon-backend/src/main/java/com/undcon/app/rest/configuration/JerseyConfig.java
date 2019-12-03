package com.undcon.app.rest.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.undcon.app.model.ProductCategoryEntity;
import com.undcon.app.rest.apis.ConfigApi;
import com.undcon.app.rest.apis.CustomerApi;
import com.undcon.app.rest.apis.EmployeeApi;
import com.undcon.app.rest.apis.ExpenseApi;
import com.undcon.app.rest.apis.IncomeApi;
import com.undcon.app.rest.apis.LoginApi;
import com.undcon.app.rest.apis.MenuTemplateApi;
import com.undcon.app.rest.apis.ProductApi;
import com.undcon.app.rest.apis.ProviderApi;
import com.undcon.app.rest.apis.ServiceApi;
import com.undcon.app.rest.apis.TenantApi;
import com.undcon.app.rest.apis.UserApi;
import com.undcon.app.rest.filters.TenantNameFilter;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		// Register the Filters e Login:
		register(TenantNameFilter.class);
		register(LoginApi.class);
		
		// Register the Resources:
		register(ConfigApi.class);
		
		register(CustomerApi.class);
		register(ProviderApi.class);
		register(EmployeeApi.class);
		
		
		register(ProductCategoryEntity.class);
		register(ProductApi.class);
		
		register(ServiceApi.class);
		register(TenantApi.class);
		register(UserApi.class);
		register(MenuTemplateApi.class);
		
		register(ExpenseApi.class);
		register(IncomeApi.class);

		// Uncomment to disable WADL Generation:
		// property("jersey.config.server.wadl.disableWadl", true);

		// Uncomment to add Request Tracing:
		// property("jersey.config.server.tracing.type", "ALL");
		// property("jersey.config.server.tracing.threshold", "TRACE");
	}
}
