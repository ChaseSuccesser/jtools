package com.ligx.processor;

import com.ligx.metrics.Metrics;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public interface MetricsProcessor<T extends Metrics> {

    void beforeProcess(long processId);

    void process(long processId, T t, long startMillTime, long endMillTime);

    void afterProcess(long processId, long startMillTime, long endMillTime);
}
