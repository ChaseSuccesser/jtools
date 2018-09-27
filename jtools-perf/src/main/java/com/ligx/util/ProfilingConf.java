package com.ligx.util;

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

    public static final int DEFAULT_MOST_TIME_THRESHOLD = 1000 * 10;
    public static final int DEFAULT_BACKUP_RECORDERS_COUNT = 1;
    public static final int DEFAULT_MILL_TIME_SLICE = 1000 * 60;

    // 方法执行时间最大阈值，单位ms，默认10000
    private int mostTimeThreshold;

    // 配置备份Recorders的数量，默认为1，最小为1，最大为8
    private int backupRecordersCount;

    // 配置时间片，单位为ms，默认60s
    private long millTimeSlice;



    ///////////////// getter/setter ///////////////////////////
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
}
