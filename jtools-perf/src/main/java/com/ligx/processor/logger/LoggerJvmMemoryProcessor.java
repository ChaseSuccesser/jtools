package com.ligx.processor.logger;

import com.ligx.formatter.JvmMemoryMetricsFormatter;
import com.ligx.metrics.impl.JvmMemoryMetrics;
import com.ligx.processor.MetricsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: ligongxing.
 * Date: 2018年10月08日.
 */
public class LoggerJvmMemoryProcessor implements MetricsProcessor<JvmMemoryMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerJvmMemoryProcessor.class);

    private JvmMemoryMetricsFormatter formatter = new JvmMemoryMetricsFormatter();

    @Override
    public void beforeProcess(long processId) {

    }

    @Override
    public void process(long processId, JvmMemoryMetrics jvmMemoryMetrics, long startMillTime, long endMillTime) {
        LOGGER.info(formatter.format(jvmMemoryMetrics, startMillTime, endMillTime));
    }

    @Override
    public void afterProcess(long processId, long startMillTime, long endMillTime) {

    }
}
