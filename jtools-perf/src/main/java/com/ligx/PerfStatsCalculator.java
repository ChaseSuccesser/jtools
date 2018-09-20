package com.ligx;

import com.ligx.recorder.AccurateRecorder;

/**
 * Author: ligongxing.
 * Date: 2018年09月20日.
 */
public class PerfStatsCalculator {

    private static final ThreadLocal<int[]> threadLocal = new ThreadLocal<int[]>(){
        @Override
        protected int[] initialValue() {
            return new int[MethodMetrics.getPercentiles().length];
        }
    };

    public static MethodMetrics calPerfStats(AccurateRecorder recorder, MethodTag methodTag, long startMillTime, long endMillTime) {
        int effectiveCount = recorder.getEffectiveCount();
        int[] sortedCostTimes = new int[effectiveCount * 2]; // todo 重用数组，避免gc
        recorder.fillSortedCostTimes(sortedCostTimes);
        return calPerfStats(sortedCostTimes, effectiveCount, methodTag, startMillTime, endMillTime);
    }


    private static MethodMetrics calPerfStats(int[] sortedCostTimes, int effectiveCount, MethodTag methodTag, long startMillTime, long endMillTime) {
        if (sortedCostTimes == null || sortedCostTimes.length == 0) {
            return MethodMetrics.getInstance(methodTag, startMillTime, endMillTime);
        }
        long[] pair = getTotalCostTimeAndTotalCount(sortedCostTimes);
        long totalCostTime = pair[0];
        int totalCount = (int) pair[1];
        double avgCostTime = ((double) totalCostTime) / totalCount;
        int minCostTime = sortedCostTimes[0];
        int maxCostTime = sortedCostTimes[effectiveCount * 2 - 2];

        MethodMetrics methodMetrics = MethodMetrics.getInstance(methodTag, startMillTime, endMillTime);
        methodMetrics.setTotalCount(totalCount);
        methodMetrics.setAvgTime(avgCostTime);
        methodMetrics.setMaxTime(maxCostTime);
        methodMetrics.setMinTime(minCostTime);

        int[] topPerIndexArr = getTopPercentileIndexArr(totalCount);
        int[] tpArr = methodMetrics.getTpArr();
        int countStats = 0;
        int perIndex = 0;
        for (int i = 0; i < sortedCostTimes.length; i += 2) {
            int costTime = sortedCostTimes[i];
            int count = sortedCostTimes[i + 1];
            countStats += count;
            if (countStats > topPerIndexArr[perIndex]) {
                tpArr[perIndex] = costTime;
                perIndex++;
            }
        }
        return methodMetrics;
    }


    private static long[] getTotalCostTimeAndTotalCount(int[] sortedCostTimes) {
        long[] result = {0L, 0L};
        if (sortedCostTimes == null || sortedCostTimes.length == 0) {
            return result;
        }
        for (int i = 0; i < sortedCostTimes.length; i+=2) {
            int costTime = sortedCostTimes[i];
            int count = sortedCostTimes[i + 1];
            result[0] += costTime * count;
            result[1] += count;
        }
        return result;
    }

    private static int[] getTopPercentileIndexArr(int totalCount) {
        int[] result = threadLocal.get();
        double[] percentiles = MethodMetrics.getPercentiles();
        for (int i = 0; i < percentiles.length; i++) {
            result[i] = getIndex(totalCount, percentiles[i]);
        }
        return result;
    }

    private static int getIndex(int totalCount, double percentile) {
        return (int) Math.ceil(totalCount * percentile);
    }
}
