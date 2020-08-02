package com.undcon.app.utils;


public class NumberUtils {

    public static boolean isNullOrZero(Long value) {
        return value == null || value == 0L;
    }
    
    public static boolean isNullOrZero(Integer value) {
        return value == null || value == 0;
    }
    
    public static boolean longIsPositiveValue(Long value) {
        return value != null && value > 0L;
    }
}
