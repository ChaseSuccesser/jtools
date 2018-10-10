package com.ligx;

import com.ligx.base.PropertiesValue;
import com.ligx.metrics.impl.JvmGCMetrics;
import com.ligx.metrics.impl.JvmMemoryMetrics;
import com.ligx.processor.MetricsProcessor;
import com.ligx.processor.influxdb.InfluxDBJvmGCProcessor;
import com.ligx.processor.influxdb.InfluxDBJvmMemoryProcessor;
import com.ligx.processor.logger.LoggerJvmGCProcessor;
import com.ligx.processor.logger.LoggerJvmMemoryProcessor;
import com.ligx.util.ProfilingConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/10/10.
 */
public class JvmMetricsScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JvmMetricsScheduler.class);

    private static final ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    private static MetricsProcessor<JvmMemoryMetrics> jvmMemoryMetricsProcessor;
    private static MetricsProcessor<JvmGCMetrics> jvmGCMetricsMetricsProcessor;

    private static void init() {
        switch (ProfilingConf.getInstance().getMethodMetricsProcessor()) {
            case PropertiesValue.LOGGER_METHOD_METRICS_PROCESSOR:
                jvmMemoryMetricsProcessor = new LoggerJvmMemoryProcessor();
                jvmGCMetricsMetricsProcessor = new LoggerJvmGCProcessor();
                break;
            case PropertiesValue.INFLUXDB_METHOD_METRICS_PROCESSOR:
                jvmMemoryMetricsProcessor = new InfluxDBJvmMemoryProcessor();
                jvmGCMetricsMetricsProcessor = new InfluxDBJvmGCProcessor();
                break;
            default:
                jvmMemoryMetricsProcessor = new LoggerJvmMemoryProcessor();
                jvmGCMetricsMetricsProcessor = new LoggerJvmGCProcessor();
        }
    }

    public static void initScheduleTask() {
        init();
        int delay = 10;
        scheduledExecutor.scheduleAtFixedRate(() -> {
            long currentMill = System.currentTimeMillis();
            long timeSliceStartMillTime = currentMill - delay * 1000;

            processJvmMemoryMetrics(timeSliceStartMillTime, currentMill);
            processJvmGCMetrics(timeSliceStartMillTime, currentMill);
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

    private static void processJvmGCMetrics(long timeSliceStartMillTime, long timeSliceEndMillTime) {
        try {
            jvmGCMetricsMetricsProcessor.process(timeSliceStartMillTime, JvmGCMetrics.newInstance(),
                    timeSliceStartMillTime, timeSliceEndMillTime);
        } catch (Exception e) {
            LOGGER.error("JvmMetricsScheduler#processJvmGCMetrics,", e);
        }
    }
}
