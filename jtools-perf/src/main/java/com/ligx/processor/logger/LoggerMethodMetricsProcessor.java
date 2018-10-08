package com.ligx.processor.logger;

import com.ligx.formatter.MethodMetricsFormatter;
import com.ligx.metrics.impl.MethodMetrics;
import com.ligx.processor.MetricsProcessor;
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
public class LoggerMethodMetricsProcessor implements MetricsProcessor<MethodMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMethodMetricsProcessor.class);

    private Map<Long, List<MethodMetrics>> metricsMap = new ConcurrentHashMap<>();

    private MethodMetricsFormatter formatter = new MethodMetricsFormatter();

    @Override
    public void beforeProcess(long processId) {
        metricsMap.put(processId, new ArrayList<>());
    }

    @Override
    public void process(long processId, MethodMetrics metrics, long startMillTime, long endMillTime) {
        List<MethodMetrics> methodMetricsList = metricsMap.get(processId);
        methodMetricsList.add(metrics);
    }

    @Override
    public void afterProcess(long processId, long startMillTime, long endMillTime) {
        List<MethodMetrics> methodMetricsList = metricsMap.get(processId);
        if (CollectionUtils.isNotEmpty(methodMetricsList)) {
            LOGGER.info(formatter.format(methodMetricsList, startMillTime, endMillTime));
        }
    }
}
