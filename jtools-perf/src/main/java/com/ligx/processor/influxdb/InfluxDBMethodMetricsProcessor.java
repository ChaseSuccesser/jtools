package com.ligx.processor.influxdb;

import com.ligx.metrics.impl.MethodMetrics;
import com.ligx.processor.MetricsProcessor;
import com.ligx.tag.MethodTag;
import com.ligx.util.DbUtil;
import com.ligx.util.ProfilingConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: ligongxing.
 * Date: 2018/09/27.
 */
public class InfluxDBMethodMetricsProcessor implements MetricsProcessor<MethodMetrics> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfluxDBMethodMetricsProcessor.class);

    private static final int MAX_LENGTH = 512;

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
    public void process(long processId, MethodMetrics metrics, long startMillTime, long endMillTime) {
        StringBuilder sb = sbThreadLocal.get();
        try {
            DbUtil.write(createLineProtocol(metrics, startMillTime * 1000 * 1000L, sb));
        } catch (Throwable e){
            LOGGER.error("InfluxDBMethodMetricsProcessor#process, processId={}, MethodMetrics={}", processId, metrics, e);
        } finally {
            sb.setLength(0);
        }
    }

    /**
     * 生成InfluxDB的line protocol
     */
    private String createLineProtocol(MethodMetrics methodMetrics, long startNanos, StringBuilder sb) {
        int suitSize = getSuitSize(methodMetrics);
        if (suitSize > MAX_LENGTH) {
            sb = new StringBuilder(suitSize);
        }
        // 把'应用名'作为InfluxDB的表名，'类名'和'类名.方法名'做为表的索引(Tag)，各个性能指标为列数据(Field)
        sb.append(ProfilingConf.getInstance().getAppName().replaceAll("-", "_"))
                .append(",ClassName=").append(methodMetrics.getMethodTag().getClassName())
                .append(",Method=").append(methodMetrics.getMethodTag().getSimpleDesc())
                .append(" QPS=").append(methodMetrics.getQPS()).append("i")
                .append(",Avg=").append(methodMetrics.getAvgTime()).append("i")
                .append(",Min=").append(methodMetrics.getMinTime()).append("i")
                .append(",Max=").append(methodMetrics.getMaxTime()).append("i")
                .append(",Count=").append(methodMetrics.getTotalCount()).append("i")
                .append(",TP50=").append(methodMetrics.getTP50()).append("i")
                .append(",TP90=").append(methodMetrics.getTP90()).append("i")
                .append(",TP95=").append(methodMetrics.getTP95()).append("i")
                .append(",TP99=").append(methodMetrics.getTP99()).append("i")
                .append(",TP999=").append(methodMetrics.getTP999()).append("i")
                .append(",TP9999=").append(methodMetrics.getTP9999()).append("i")
                .append(",TP99999=").append(methodMetrics.getTP99999()).append("i")
                .append(",TP100=").append(methodMetrics.getTP100()).append("i")
                .append(" ").append(startNanos);
        return sb.toString();
    }

    private int getSuitSize(MethodMetrics methodMetrics) {
        MethodTag methodTag = methodMetrics.getMethodTag();
        return methodTag.getClassName().length()
                + 8 + methodTag.getSimpleDesc().length()//Method
                + 5 + 6 + 1//QPS
                + 5 + 7 + 1//Avg
                + 5 + 3 + 1//Min
                + 5 + 5 + 1//Max
                + 7 + 8 + 1//Count
                + 6 + 5 + 1//TP50
                + 6 + 5 + 1//TP90
                + 6 + 5 + 1//TP95
                + 6 + 5 + 1//TP99
                + 7 + 5 + 1//TP999
                + 8 + 5 + 1//TP9999
                + 9 + 5 + 1//TP99999
                + 7 + 5 + 1//TP100
                + 1 + 21//startNanos
                ;
    }

}
