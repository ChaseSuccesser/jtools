package com.ligx;

import com.ligx.metrics.impl.JvmMemoryMetrics;
import com.ligx.processor.influxdb.InfluxDBJvmMemoryProcessor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public class JvmMemoryScheduler {

    private static final ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    private static InfluxDBJvmMemoryProcessor jvmMemoryProcessor = new InfluxDBJvmMemoryProcessor();

    public static void initScheduleTask() {
        int delay = 10;

        scheduledExecutor.scheduleAtFixedRate(() -> {
            long currentMill = System.currentTimeMillis();
            long timeSliceStartMillTime = currentMill - delay * 1000;

            processJvmMemoryMetrics(timeSliceStartMillTime, currentMill);

        }, delay, delay, TimeUnit.SECONDS);
    }

    private static void processJvmMemoryMetrics(long timeSliceStartMillTime, long timeSliceEndMillTime) {
        jvmMemoryProcessor.process(timeSliceStartMillTime, JvmMemoryMetrics.newInstance(),
                timeSliceStartMillTime, timeSliceStartMillTime + timeSliceEndMillTime);
    }
}
