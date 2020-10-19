package com.undcon.app.rest.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.undcon.app.rest.apis.BankCheckApi;
import com.undcon.app.rest.apis.ConfigApi;
import com.undcon.app.rest.apis.CustomerApi;
import com.undcon.app.rest.apis.DashBoardApi;
import com.undcon.app.rest.apis.EmployeeApi;
import com.undcon.app.rest.apis.ExpenseApi;
import com.undcon.app.rest.apis.IncomeApi;
import com.undcon.app.rest.apis.LoginApi;
import com.undcon.app.rest.apis.MenuTemplateApi;
import com.undcon.app.rest.apis.PermissionApi;
import com.undcon.app.rest.apis.ProductApi;
import com.undcon.app.rest.apis.ProductCategoryApi;
import com.undcon.app.rest.apis.ProviderApi;
import com.undcon.app.rest.apis.PurchaseApi;
import com.undcon.app.rest.apis.PurchaseItemProductApi;
import com.undcon.app.rest.apis.PurchaseItemServiceTypeApi;
import com.undcon.app.rest.apis.SaleApi;
import com.undcon.app.rest.apis.SaleItemProductApi;
import com.undcon.app.rest.apis.SaleItemServiceTypeApi;
import com.undcon.app.rest.apis.ServiceTypeApi;
import com.undcon.app.rest.apis.SystemSalesmanApi;
import com.undcon.app.rest.apis.TenantApi;
import com.undcon.app.rest.apis.UserApi;
import com.undcon.app.rest.exception.ErrorResponseHandler;
import com.undcon.app.rest.filters.RequestFilter;
import com.undcon.app.rest.filters.ResponseFilter;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		// Register the Filters e Login:
		register(RequestFilter.class);
		register(ResponseFilter.class);
		register(LoginApi.class);
		
		// Register the Resources:
		register(ConfigApi.class);
		
		register(IncomeApi.class);
		
		register(CustomerApi.class);
		register(ProviderApi.class);
		register(EmployeeApi.class);
		
		
		register(ProductCategoryApi.class);
		register(ProductApi.class);
		
		register(ServiceTypeApi.class);
		register(TenantApi.class);
		register(UserApi.class);

		register(MenuTemplateApi.class);
		register(PermissionApi.class);
		
		register(ExpenseApi.class);
		register(IncomeApi.class);

		register(PurchaseApi.class);
		register(PurchaseItemProductApi.class);
		register(PurchaseItemServiceTypeApi.class);
		
		register(SaleApi.class);
		register(SaleItemProductApi.class);
		register(SaleItemServiceTypeApi.class);
		
		register(BankCheckApi.class);
		
		register(SystemSalesmanApi.class);
		
		
		register(DashBoardApi.class);
		
		register(ErrorResponseHandler.class);
		// Uncomment to disable WADL Generation:
		// property("jersey.config.server.wadl.disableWadl", true);

		// Uncomment to add Request Tracing:
		// property("jersey.config.server.tracing.type", "ALL");
		// property("jersey.config.server.tracing.threshold", "TRACE");
	}
}
