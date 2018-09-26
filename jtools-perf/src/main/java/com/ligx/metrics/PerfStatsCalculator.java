package com.ligx.metrics;

import com.ligx.recorder.AccurateRecorder;
import com.ligx.tag.MethodTag;
import com.ligx.util.ChunkPool;
import com.ligx.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Author: ligongxing.
 * Date: 2018年09月20日.
 */
public class PerfStatsCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerfStatsCalculator.class);

    private static final ThreadLocal<int[]> threadLocal = new ThreadLocal<int[]>(){
        @Override
        protected int[] initialValue() {
            return new int[MethodMetrics.getPercentiles().length];
        }
    };

    public static MethodMetrics calPerfStats(AccurateRecorder recorder, MethodTag methodTag, long startMillTime, long endMillTime) {
        int[] sortedCostTimes = null;
        try {
            int effectiveCount = recorder.getEffectiveCount();
            sortedCostTimes = ChunkPool.getInstance().getChunk(effectiveCount * 2);
            recorder.fillSortedCostTimes(sortedCostTimes);
            return calPerfStats(sortedCostTimes, effectiveCount, methodTag, startMillTime, endMillTime);
        } catch (Exception e) {
            LOGGER.error("PerfStatsCalculator#calPerfStats, recorder={}, MethodTag={}, startTime={}, endTime={}",
                    recorder, methodTag,
                    TimeUtil.format(new Date(startMillTime), TimeUtil.YMDHMS_FORMAT),
                    TimeUtil.format(new Date(endMillTime), TimeUtil.YMDHMS_FORMAT),
                    e);
        } finally {
            ChunkPool.getInstance().returnChunk(sortedCostTimes);
        }
        return MethodMetrics.getInstance(methodTag, startMillTime, endMillTime);
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
        methodMetrics.setTotalCount(totalCount); // 总调用次数
        methodMetrics.setAvgTime(avgCostTime);   // 平均耗时
        methodMetrics.setMaxTime(maxCostTime);   // 最大耗时
        methodMetrics.setMinTime(minCostTime);   // 最小耗时

        // 计算各种TP指标
        int[] topPerIndexArr = getTopPercentileIndexArr(totalCount);
        int[] tpArr = methodMetrics.getTpArr();
        int countStats = 0;
        int perIndex = 0;
        for (int i = 0; i < sortedCostTimes.length; i += 2) {
            int costTime = sortedCostTimes[i];
            int count = sortedCostTimes[i + 1];
//            // sortedRecords中只有第0位的响应时间可以为0
//            if (i > 0 && costTime <= 0) {
//                break;
//            }
            if (perIndex == MethodMetrics.getPercentiles().length) {
                break;
            }
            countStats += count;
            System.out.println("perIndex: " + perIndex);  // todo delete
            if (countStats >= topPerIndexArr[perIndex]) {
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
        // todo delete
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]).append(",");
        }
        System.out.println(sb.toString());
        return result;
    }

    private static int getIndex(int totalCount, double percentile) {
        return (int) Math.ceil(totalCount * percentile);
    }
}
