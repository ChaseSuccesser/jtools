package com.ligx.processor;

import com.ligx.formatter.MethodMetricsFormatter;
import com.ligx.metrics.MethodMetrics;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class LoggerMethodMetricProcessor implements MethodMetricsProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMethodMetricProcessor.class);

    private Map<Long, List<MethodMetrics>> metricsMap = new ConcurrentHashMap<>();

    private MethodMetricsFormatter formatter = new MethodMetricsFormatter();

    @Override
    public void beforeProcess(long processId) {
        metricsMap.put(processId, new ArrayList<>());
    }

    @Override
    public void process(long processId, MethodMetrics methodMetrics, long startMillTime, long endMillTime) {
        List<MethodMetrics> methodMetricsList = metricsMap.get(processId);
        methodMetricsList.add(methodMetrics);
    }

    @Override
    public void afterProcess(long processId, long startMillTime, long endMillTime) {
        List<MethodMetrics> methodMetricsList = metricsMap.get(processId);
        if (CollectionUtils.isNotEmpty(methodMetricsList)) {
            LOGGER.info(formatter.format(methodMetricsList, startMillTime, endMillTime));
        }
    }
}
