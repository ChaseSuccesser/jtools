package com.ligx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Author: ligongxing.
 * Date: 2018/09/27.
 */
public class ProfilingProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingProperties.class);

    private static Properties prop;

    public static void initial(Properties properties) {
        prop = properties;
    }


    private static String getStr(String key) {
        return prop.getProperty(key);
    }

    public static String getStr(String key, String defaultValue) {
        if (prop == null) {
            return defaultValue;
        }
        String result = getStr(key);
        if (result != null) {
            return result;
        }
        return defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        if (prop == null) {
            return defaultValue;
        }
        String result = getStr(key);
        if (result == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(result);
        } catch (Exception e) {
            LOGGER.error("ProfilingProperties.getInt(" + key + ", " + defaultValue + ")", e);
        }
        return defaultValue;
    }

    public static long getLong(String key, long defaultValue) {
        if (prop == null) {
            return defaultValue;
        }
        String result = getStr(key);
        if (result == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(result);
        } catch (Exception e) {
            LOGGER.error("ProfilingProperties.getLong(" + key + ", " + defaultValue + ")", e);
        }
        return defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (prop == null) {
            return defaultValue;
        }
        String result = getStr(key);
        if (result != null) {
            return result.equalsIgnoreCase("true");
        }
        return defaultValue;
    }
}
