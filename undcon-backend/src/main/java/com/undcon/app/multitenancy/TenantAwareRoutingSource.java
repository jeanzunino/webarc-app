package com.undcon.app.multitenancy;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class TenantAwareRoutingSource extends AbstractRoutingDataSource {
	
	public TenantAwareRoutingSource() {
	}
    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalStorage.getTenantName();
    }
    
    public void teste() {
    	System.err.println("teste");
    }

}
