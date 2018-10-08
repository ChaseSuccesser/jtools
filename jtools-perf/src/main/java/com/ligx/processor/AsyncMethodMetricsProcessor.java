package com.ligx.processor;

import com.ligx.metrics.impl.MethodMetrics;
import com.ligx.util.ProfilingConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public class AsyncMethodMetricsProcessor implements MetricsProcessor<MethodMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncMethodMetricsProcessor.class);

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3,
            ProfilingConf.getInstance().getMillTimeSlice() * 2,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(500), new ThreadPoolExecutor.DiscardPolicy());

    private MetricsProcessor<MethodMetrics> target;

    public AsyncMethodMetricsProcessor(MetricsProcessor<MethodMetrics> target) {
        this.target = target;
    }

    @Override
    public void beforeProcess(long processId) {
        if (target == null) {
            return;
        }
        executor.execute(() -> {
            try {
                target.beforeProcess(processId);
            } catch (Exception e) {
                LOGGER.error("AsyncMethodMetricsProcessor#beforeProcess, processId={}", processId, e);
            }
        });
    }

    @Override
    public void process(long processId, MethodMetrics methodMetrics, long startMillTime, long endMillTime) {
        if (target == null) {
            return;
        }
        executor.execute(() -> {
            try {
                target.process(processId, methodMetrics, startMillTime, endMillTime);
            } catch (Exception e) {
                LOGGER.error("AsyncMethodMetricsProcessor#process, processId={}, MethodMetrics={}",
                        processId, methodMetrics, e);
            }
        });
    }

    @Override
    public void afterProcess(long processId, long startMillTime, long endMillTime) {
        if (target == null) {
            return;
        }
        executor.execute(() -> {
            try {
                target.afterProcess(processId, startMillTime, endMillTime);
            } catch (Exception e) {
                LOGGER.error("AsyncMethodMetricsProcessor#afterProcess, processId={}", processId, e);
            }
        });
    }
}
