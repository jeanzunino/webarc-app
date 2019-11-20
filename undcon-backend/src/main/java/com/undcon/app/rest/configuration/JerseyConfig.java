package com.undcon.app.rest.configuration;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.undcon.app.rest.apis.CustomerApi;
import com.undcon.app.rest.apis.EmployeeApi;
import com.undcon.app.rest.apis.TenantApi;
import com.undcon.app.rest.apis.UserApi;
import com.undcon.app.rest.filters.TenantNameFilter;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // Register the Filters:
        register(TenantNameFilter.class);

        // Register the Resources:
        register(CustomerApi.class);
        register(TenantApi.class);
        register(EmployeeApi.class);
        register(UserApi.class);

        // Uncomment to disable WADL Generation:
        //property("jersey.config.server.wadl.disableWadl", true);

        // Uncomment to add Request Tracing:
        //property("jersey.config.server.tracing.type", "ALL");
        //property("jersey.config.server.tracing.threshold", "TRACE");
    }
}
