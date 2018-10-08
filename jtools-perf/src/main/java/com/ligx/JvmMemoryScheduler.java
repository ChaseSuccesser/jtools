package com.ligx;

import com.ligx.base.PropertiesValue;
import com.ligx.metrics.impl.JvmMemoryMetrics;
import com.ligx.processor.MetricsProcessor;
import com.ligx.processor.influxdb.InfluxDBJvmMemoryProcessor;
import com.ligx.processor.logger.LoggerJvmMemoryProcessor;
import com.ligx.util.ProfilingConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public class JvmMemoryScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JvmMemoryScheduler.class);

    private static final ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    private static MetricsProcessor<JvmMemoryMetrics> jvmMemoryMetricsProcessor;

    private static void init() {
        switch (ProfilingConf.getInstance().getMethodMetricsProcessor()) {
            case PropertiesValue.LOGGER_METHOD_METRICS_PROCESSOR:
                jvmMemoryMetricsProcessor = new LoggerJvmMemoryProcessor();
                break;
            case PropertiesValue.INFLUXDB_METHOD_METRICS_PROCESSOR:
                jvmMemoryMetricsProcessor = new InfluxDBJvmMemoryProcessor();
                break;
            default:
                jvmMemoryMetricsProcessor = new LoggerJvmMemoryProcessor();
        }
    }

    public static void initScheduleTask() {
        init();
        int delay = 10;
        scheduledExecutor.scheduleAtFixedRate(() -> {
            long currentMill = System.currentTimeMillis();
            long timeSliceStartMillTime = currentMill - delay * 1000;

            processJvmMemoryMetrics(timeSliceStartMillTime, currentMill);

        }, delay, delay, TimeUnit.SECONDS);
    }

    private static void processJvmMemoryMetrics(long timeSliceStartMillTime, long timeSliceEndMillTime) {
        try {
            jvmMemoryMetricsProcessor.process(timeSliceStartMillTime, JvmMemoryMetrics.newInstance(),
                    timeSliceStartMillTime, timeSliceStartMillTime + timeSliceEndMillTime);
        } catch (Exception e) {
            LOGGER.error("JvmMemoryScheduler#processJvmMemoryMetrics,", e);
        }
    }
}
