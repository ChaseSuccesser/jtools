package com.ligx.recorder;

import com.ligx.util.ProfilingConf;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Author: ligongxing.
 * Date: 2018/09/19.
 */
public class AccurateRecorder {

    private int methodTagId;

    private boolean hasRecord;

    private AtomicIntegerArray timingArr;

    private ConcurrentHashMap<Integer, Integer> timingMap;

    private AccurateRecorder(int methodTagId) {
        int maxExecutionTimeThreshold = ProfilingConf.getInstance().getMaxExecutionTimeThreshold();
        this.methodTagId = methodTagId;
        this.timingArr = new AtomicIntegerArray(maxExecutionTimeThreshold + 1);
        this.timingMap = new ConcurrentHashMap<>();
    }

    public static AccurateRecorder getInstance(int methodTagId) {
        return new AccurateRecorder(methodTagId);
    }

    public void recordTime(long startMillTime, long endMillTime) {
        if (startMillTime > endMillTime) {
            return;
        }
        hasRecord = true;

        int costTime = (int) (endMillTime - startMillTime);
        if (costTime < timingArr.length()) {
            timingArr.incrementAndGet(costTime);
            return;
        }

        timingMap.merge(costTime, 1, (oldVal, newVal) -> oldVal + newVal);
    }

    public void fillSortedCostTimes(int[] arr) {
        int index = 0;
        for (int i = 0; i < timingArr.length(); i++) {
            int count = timingArr.get(i);
            if (count > 0) {
                arr[index++] = i;
                arr[index++] = count;
            }
        }
        if (MapUtils.isNotEmpty(timingMap)) {
            List<Integer> costTimeList = new ArrayList<>(timingMap.keySet());
            Collections.sort(costTimeList);
            for (int i = 0; i < costTimeList.size(); i++) {
                int costTime = costTimeList.get(i);
                int count = timingMap.get(costTime);
                if (count > 0) {
                    arr[index++] = costTime;
                    arr[index++] = count;
                }
            }
        }
    }

    public int getEffectiveCount() {
        int result = 0;
        for (int i = 0; i < timingArr.length(); i++) {
            int count = timingArr.get(i);
            if (count > 0) {
                result++;
            }
        }
        for (Map.Entry<Integer, Integer> entry : timingMap.entrySet()) {
            int count = entry.getValue();
            if (count > 0) {
                result++;
            }
        }
        return result;
    }

    public void resetRecord() {
        for (int i = 0; i < timingArr.length(); i++) {
            timingArr.set(i, 0);
        }
        timingMap.clear();

        hasRecord = false;
    }


    // ================= getter/setter =============

    public int getMethodTagId() {
        return methodTagId;
    }

    public boolean isHasRecord() {
        return hasRecord;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccurateRecorder{")
                .append("methodTagId=").append(methodTagId)
                .append(", hasRecord=").append(hasRecord)
                .append("\n");
        for (int i = 0, length = timingArr.length(); i < length; i++) {
            if (timingArr.get(i) > 0) {
                sb.append("costTime=").append(i).append(",").append("count=").append(timingArr.get(i)).append("\n");
            }
        }
        for (Map.Entry<Integer, Integer> entry : timingMap.entrySet()) {
            Integer costTime = entry.getKey();
            Integer count = entry.getValue();
            if (count != null && count > 0) {
                sb.append("costTime=").append(costTime).append(",").append("count=").append(count).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
