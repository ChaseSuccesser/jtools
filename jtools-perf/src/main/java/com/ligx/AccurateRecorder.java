package com.ligx;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
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

    private AccurateRecorder(int methodTagId, int mostTimeThreshold) {
        this.methodTagId = methodTagId;
        this.timingArr = new AtomicIntegerArray(mostTimeThreshold + 1);
        this.timingMap = new ConcurrentHashMap<>();
    }

    public static AccurateRecorder getInstance(int methodTagId, int mostTimeThreshold) {
        return new AccurateRecorder(methodTagId, mostTimeThreshold);
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
}
