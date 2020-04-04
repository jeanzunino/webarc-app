package com.undcon.app.multitenancy;

import com.undcon.app.model.UserEntity;

public class ThreadLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal<>();
    private static ThreadLocal<UserEntity> user = new ThreadLocal<>();

    public static void setTenantName(String tenantName) {
        tenant.set(tenantName);
    }

    public static String getTenantName() {
        return tenant.get();
    }
    
    
    public static void setUser(UserEntity id) {
        user.set(id);
    }
    
    
    public static UserEntity getUser() {
        return user.get();
    }

}

