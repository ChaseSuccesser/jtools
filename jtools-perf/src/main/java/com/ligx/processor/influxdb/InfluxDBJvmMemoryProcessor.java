package com.ligx.processor.influxdb;

import com.ligx.metrics.impl.JvmMemoryMetrics;
import com.ligx.processor.MetricsProcessor;
import com.ligx.util.DbUtil;
import com.ligx.util.ProfilingConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public class InfluxDBJvmMemoryProcessor implements MetricsProcessor<JvmMemoryMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfluxDBJvmMemoryProcessor.class);

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
    public void process(long processId, JvmMemoryMetrics metrics, long startMillTime, long endMillTime) {
        StringBuilder sb = sbThreadLocal.get();
        try {
            DbUtil.write(createLineProtocol(metrics, startMillTime * 1000 * 1000L, sb));
        } catch (Throwable e){
            LOGGER.error("InfluxDBJvmMemoryProcessor#process,processId={}, JvmMemoryMetrics={}", processId, metrics, e);
        } finally {
            sb.setLength(0);
        }
    }


    /**
     * 生成InfluxDB的line protocol
     */
    private String createLineProtocol(JvmMemoryMetrics metrics, long startNanos, StringBuilder sb) {
        sb.append("jvm_memory_metrics")
                .append(",AppName=").append(ProfilingConf.getInstance().getAppName().replaceAll("-", "_"))
                .append(" heapUsedMemory=").append(metrics.getHeapUsedMemory())
                .append(",heapMaxMemory=").append(metrics.getHeapMaxMemory())
                .append(",oldGenUsedMemory=").append(metrics.getOldGenUsedMemory())
                .append(",oldGenMaxMemory=").append(metrics.getOldGenMaxMemory())
                .append(",edenUsedSpace=").append(metrics.getEdenUsedSpace())
                .append(",edenMaxSpace=").append(metrics.getEdenMaxSpace())
                .append(",survivorUsedSpace=").append(metrics.getSurvivorUsedSpace())
                .append(",survivorMaxSpace=").append(metrics.getSurvivorMaxSpace())
                .append(",metaspaceUsedSpace=").append(metrics.getMetaspaceUsedSpace())
                .append(",nonHeapUsedMemory=").append(metrics.getNonHeapUsedMemory())
                .append(",nonHeapMaxMemory=").append(metrics.getNonHeapMaxMemory())
                .append(" ").append(startNanos);
        return sb.toString();
    }
}
