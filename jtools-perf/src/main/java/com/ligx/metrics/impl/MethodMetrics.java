package com.ligx.metrics.impl;

import com.ligx.metrics.Metrics;
import com.ligx.tag.MethodTag;

/**
 * Author: ligongxing.
 * Date: 2018/09/19.
 */
public class MethodMetrics extends Metrics {

    private static final long serialVersionUID = -2308772138574732120L;

    private MethodTag methodTag;

    private static final int TP_50_IDX = 0;
    private static final int TP_90_IDX = 1;
    private static final int TP_95_IDX = 2;
    private static final int TP_99_IDX = 3;
    private static final int TP_999_IDX = 4;
    private static final int TP_9999_IDX = 5;
    private static final int TP_99999_IDX = 6;
    private static final int TP_100_IDX = 7;

    //注意：tpArr和TOP_PERCENTILE_ARR的大小请保持一致！！！！
    private static final double[] TOP_PERCENTILE_ARR = {0.5D, 0.9D, 0.95D, 0.99D, 0.999D, 0.9999D, 0.99999D, 1.0D};

    //注意：tpArr和TOP_PERCENTILE_ARR的大小请保持一致！！！！
    private final int[] tpArr = {-1, -1, -1, -1, -1, -1, -1, -1};
    private int maxTime = -1; //ms
    private int minTime = -1; //ms
    private int avgTime = -1; //ms
    private int totalCount = -1;//ms
    private long startMillTime = -1L;
    private long stopMillTime = -1L;


    private MethodMetrics(MethodTag methodTag) {
        this.methodTag = methodTag;
    }


    public int getTP50() {
        return tpArr[TP_50_IDX];
    }

    public int getTP90() {
        return tpArr[TP_90_IDX];
    }

    public int getTP95() {
        return tpArr[TP_95_IDX];
    }

    public int getTP99() {
        return tpArr[TP_99_IDX];
    }

    public int getTP999() {
        return tpArr[TP_999_IDX];
    }

    public int getTP9999() {
        return tpArr[TP_9999_IDX];
    }

    public int getTP99999() {
        return tpArr[TP_99999_IDX];
    }

    public int getTP100() {
        return tpArr[TP_100_IDX];
    }

    public int getQPS() {
        long seconds = (stopMillTime - startMillTime) / 1000;
        seconds = seconds <= 0L ? 1 : seconds;
        return (int) (totalCount / seconds);
    }

    public static MethodMetrics newInstance(MethodTag methodTag) {
        return new MethodMetrics(methodTag);
    }

    public static MethodMetrics newInstance(MethodTag methodTag, long startMillTime, long stopMillTime) {
        MethodMetrics result = new MethodMetrics(methodTag);
        result.setStartMillTime(startMillTime);
        result.setStopMillTime(stopMillTime);
        return result;
    }

    public static double[] getPercentiles() {
        return TOP_PERCENTILE_ARR;
    }


    //================= getter/setter ==================
    public MethodTag getMethodTag() {
        return methodTag;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(int avgTime) {
        this.avgTime = avgTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getStartMillTime() {
        return startMillTime;
    }

    public void setStartMillTime(long startMillTime) {
        this.startMillTime = startMillTime;
    }

    public long getStopMillTime() {
        return stopMillTime;
    }

    public void setStopMillTime(long stopMillTime) {
        this.stopMillTime = stopMillTime;
    }

    public int[] getTpArr() {
        return tpArr;
    }


    @Override
    public String toString() {
        return "MethodMetrics{" +
                "methodTag=" + methodTag +
                ", RPS=" + getQPS() +
                ", TP50=" + getTP50() +
                ", TP90=" + getTP90() +
                ", TP95=" + getTP95() +
                ", TP99=" + getTP99() +
                ", TP999=" + getTP999() +
                ", TP9999=" + getTP9999() +
                ", TP99999=" + getTP99999() +
                ", TP100=" + getTP100() +
                ", minTime=" + minTime +
                ", avgTime=" + avgTime +
                ", maxTime=" + maxTime +
                ", totalCount=" + totalCount +
                '}';
    }
}
