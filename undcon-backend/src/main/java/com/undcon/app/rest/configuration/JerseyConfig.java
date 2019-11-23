package com.undcon.app.rest.configuration;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.undcon.app.rest.apis.ConfigApi;
import com.undcon.app.rest.apis.CustomerApi;
import com.undcon.app.rest.apis.EmployeeApi;
import com.undcon.app.rest.apis.LoginApi;
import com.undcon.app.rest.apis.ProductApi;
import com.undcon.app.rest.apis.ProviderApi;
import com.undcon.app.rest.apis.ServiceApi;
import com.undcon.app.rest.apis.TenantApi;
import com.undcon.app.rest.apis.UserApi;
import com.undcon.app.rest.filters.TenantNameFilter;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // Register the Filters:
        register(TenantNameFilter.class);

        // Register the Resources:
        register(ConfigApi.class);
        register(CustomerApi.class);
        register(EmployeeApi.class);
        register(LoginApi.class);
        register(ProductApi.class);
        register(ProviderApi.class);
        register(ServiceApi.class);
        register(TenantApi.class);
        register(UserApi.class);
        // Uncomment to disable WADL Generation:
        //property("jersey.config.server.wadl.disableWadl", true);

        // Uncomment to add Request Tracing:
        //property("jersey.config.server.tracing.type", "ALL");
        //property("jersey.config.server.tracing.threshold", "TRACE");
    }
}
