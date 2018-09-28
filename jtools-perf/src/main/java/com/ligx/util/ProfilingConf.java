package com.ligx.util;

import com.ligx.base.PropertiesValue;

/**
 * Author: ligongxing.
 * Date: 2018/09/27.
 */
public class ProfilingConf {

    private static final ProfilingConf instance = new ProfilingConf();

    private ProfilingConf(){}

    public static ProfilingConf getInstance() {
        return instance;
    }

    public static final String DEFAULT_APP_NAME = "default";
    public static final int DEFAULT_MOST_TIME_THRESHOLD = 1000 * 10;
    public static final int DEFAULT_BACKUP_RECORDERS_COUNT = 3;
    public static final int DEFAULT_MILL_TIME_SLICE = 1000 * 60;
    public static final String DEFAULT_METHOD_METRICS_PROCESSOR = PropertiesValue.LOGGER_METHOD_METRICS_PROCESSOR;

    // 应用名称
    private String appName = DEFAULT_APP_NAME;

    // 方法执行时间最大阈值，单位ms，默认10000
    private int mostTimeThreshold = DEFAULT_MOST_TIME_THRESHOLD;

    // 配置备份Recorders的数量，默认为3，最小为1，最大为8
    private int backupRecordersCount = DEFAULT_BACKUP_RECORDERS_COUNT;

    // 配置时间片，单位为ms，默认60s
    private long millTimeSlice = DEFAULT_MILL_TIME_SLICE;

    // 使用什么方式处理MethodMetrics，默认log
    private String methodMetricsProcessor = DEFAULT_METHOD_METRICS_PROCESSOR;

    // InfluxDB配置
    private String influxdbUrl;
    private String influxdbUserName;
    private String influxdbPassword;


    ///////////////// getter/setter ///////////////////////////
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getMostTimeThreshold() {
        return mostTimeThreshold;
    }

    public void setMostTimeThreshold(int mostTimeThreshold) {
        this.mostTimeThreshold = mostTimeThreshold;
    }

    public int getBackupRecordersCount() {
        return backupRecordersCount;
    }

    public void setBackupRecordersCount(int backupRecordersCount) {
        this.backupRecordersCount = backupRecordersCount;
    }

    public long getMillTimeSlice() {
        return millTimeSlice;
    }

    public void setMillTimeSlice(long millTimeSlice) {
        this.millTimeSlice = millTimeSlice;
    }

    public String getMethodMetricsProcessor() {
        return methodMetricsProcessor;
    }

    public void setMethodMetricsProcessor(String methodMetricsProcessor) {
        this.methodMetricsProcessor = methodMetricsProcessor;
    }

    public String getInfluxdbUrl() {
        return influxdbUrl;
    }

    public void setInfluxdbUrl(String influxdbUrl) {
        this.influxdbUrl = influxdbUrl;
    }

    public String getInfluxdbUserName() {
        return influxdbUserName;
    }

    public void setInfluxdbUserName(String influxdbUserName) {
        this.influxdbUserName = influxdbUserName;
    }

    public String getInfluxdbPassword() {
        return influxdbPassword;
    }

    public void setInfluxdbPassword(String influxdbPassword) {
        this.influxdbPassword = influxdbPassword;
    }
}
