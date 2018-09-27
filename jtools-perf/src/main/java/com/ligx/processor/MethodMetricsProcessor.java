package com.ligx.processor;

import com.ligx.metrics.MethodMetrics;

/**
 * Author: ligongxing.
 * Date: 2018/09/27.
 */
public interface MethodMetricsProcessor {

    void beforeProcess(long processId);

    void process(long processId, MethodMetrics methodMetrics, long startMillTime, long endMillTime);

    void afterProcess(long processId, long startMillTime, long endMillTime);
}
