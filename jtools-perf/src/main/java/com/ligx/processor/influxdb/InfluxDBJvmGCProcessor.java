package com.ligx.processor.influxdb;

import com.ligx.metrics.impl.JvmGCMetrics;
import com.ligx.processor.MetricsProcessor;
import com.ligx.util.DbUtil;
import com.ligx.util.ProfilingConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: ligongxing.
 * Date: 2018/10/10.
 */
public class InfluxDBJvmGCProcessor implements MetricsProcessor<JvmGCMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfluxDBJvmGCProcessor.class);

    private ThreadLocal<StringBuilder> sbThreadLocal = new ThreadLocal<StringBuilder>() {
        @Override
        protected StringBuilder initialValue() {
            return new StringBuilder(256);
        }
    };

    @Override
    public void beforeProcess(long processId) {
    }

    @Override
    public void afterProcess(long processId, long startMillTime, long endMillTime) {
    }

    @Override
    public void process(long processId, JvmGCMetrics jvmGCMetrics, long startMillTime, long endMillTime) {
        StringBuilder sb = sbThreadLocal.get();
        try {
            DbUtil.write(createLineProtocol(jvmGCMetrics, startMillTime * 1000 * 1000L, sb));
        } catch (Throwable e){
            LOGGER.error("InfluxDBJvmGCProcessor#process, processId={}, JvmGCMetrics={}", processId, jvmGCMetrics, e);
        } finally {
            sb.setLength(0);
        }
    }

    private String createLineProtocol(JvmGCMetrics metrics, long startNanos, StringBuilder sb) {
        sb.append("jvm_gc_metrics")
                .append(",AppName=").append(ProfilingConf.getInstance().getAppName().replaceAll("-", "_"))
                .append(" YoungGCCount=").append(metrics.getYoungGCCount()).append("i")
                .append(",YoungGCTime=").append(metrics.getYoungGCTime()).append("i")
                .append(",FullGCCount=").append(metrics.getFullGCCount()).append("i")
                .append(",FullGCTime=").append(metrics.getFullGCTime()).append("i")
                .append(" ")
                .append(startNanos);
        return sb.toString();
    }

}
