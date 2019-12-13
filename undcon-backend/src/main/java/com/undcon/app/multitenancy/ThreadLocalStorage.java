package com.undcon.app.multitenancy;

public class ThreadLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal<>();
    private static ThreadLocal<Long> userId = new ThreadLocal<>();

    public static void setTenantName(String tenantName) {
        tenant.set(tenantName);
    }

    public static String getTenantName() {
        return tenant.get();
    }
    
    
    public static void setUserId(Long id) {
        userId.set(id);
    }
    
    
    public static Long getUser() {
        return userId.get();
    }

}

