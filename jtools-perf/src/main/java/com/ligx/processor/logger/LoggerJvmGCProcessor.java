package com.ligx.processor.logger;

import com.ligx.formatter.JvmGCMetricsFormatter;
import com.ligx.metrics.impl.JvmGCMetrics;
import com.ligx.processor.MetricsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: ligongxing.
 * Date: 2018/10/10.
 */
public class LoggerJvmGCProcessor implements MetricsProcessor<JvmGCMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerJvmGCProcessor.class);

    private JvmGCMetricsFormatter formatter = new JvmGCMetricsFormatter();

    @Override
    public void beforeProcess(long processId) {

    }

    @Override
    public void process(long processId, JvmGCMetrics jvmGCMetrics, long startMillTime, long endMillTime) {
        LOGGER.info(formatter.format(jvmGCMetrics, startMillTime, endMillTime));
    }

    @Override
    public void afterProcess(long processId, long startMillTime, long endMillTime) {

    }
}
