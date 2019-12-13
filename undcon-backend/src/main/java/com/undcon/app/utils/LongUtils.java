package com.undcon.app.utils;


public class LongUtils {

    public static boolean longIsNullOrZero(Long value) {
        return value == null || value == 0L;
    }
    
    public static boolean longIsPositiveValue(Long value) {
        return value != null && value > 0L;
    }
}
