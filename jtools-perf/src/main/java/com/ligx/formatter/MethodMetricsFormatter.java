package com.ligx.formatter;

import com.ligx.metrics.impl.MethodMetrics;
import com.ligx.util.TimeUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class MethodMetricsFormatter {

    public String format(List<MethodMetrics> methodMetricsList, long startMillTime, long endMillTime) {
        if (CollectionUtils.isEmpty(methodMetricsList)) {
            return "";
        }
        int maxApiLength = getMaxApiLength(methodMetricsList);

        StringBuilder sb = new StringBuilder();
        sb.append("Method Metrics [")
                .append(TimeUtil.format(new Date(startMillTime), TimeUtil.YMDHMS_FORMAT))
                .append(",")
                .append(TimeUtil.format(new Date(endMillTime), TimeUtil.YMDHMS_FORMAT))
                .append("]\n");

        String titleFormat = "%" + maxApiLength + "s%9s%9s%9s%9s%9s%10s%9s%9s%9s%9s%9s%9s%9s";
        sb.append(String.format(titleFormat,
                "Method[" + methodMetricsList.size() + "]",
                "QPS",
                "Avg(ms)",
                "Min(ms)",
                "Max(ms)",
                "Count",
                "TP50",
                "TP90",
                "TP95",
                "TP99",
                "TP999",
                "TP9999",
                "TP99999",
                "TP100"))
                .append("\n");

        String dataFormat = "%" + maxApiLength + "s%9d%9d%9d%9d%10d%9d%9d%9d%9d%9d%9d%9d%9d";
        for (MethodMetrics m : methodMetricsList) {
            if (m.getTotalCount() <= 0) {
                continue;
            }
            sb.append(String.format(dataFormat,
                    m.getMethodTag().getSimpleDesc(),
                    m.getQPS(),
                    m.getAvgTime(),
                    m.getMinTime(),
                    m.getMaxTime(),
                    m.getTotalCount(),
                    m.getTP50(),
                    m.getTP90(),
                    m.getTP95(),
                    m.getTP99(),
                    m.getTP999(),
                    m.getTP9999(),
                    m.getTP99999(),
                    m.getTP100()))
                    .append("\n");
        }
        return sb.toString();
    }

    private int getMaxApiLength(List<MethodMetrics> methodMetricsList) {
        if (CollectionUtils.isEmpty(methodMetricsList)) {
            return 0;
        }
        int length = 0;
        for (MethodMetrics methodMetrics : methodMetricsList) {
            if (methodMetrics == null || methodMetrics.getMethodTag() == null) {
                continue;
            }
            length = Math.max(length, methodMetrics.getMethodTag().getSimpleDesc().length());
        }
        return length;
    }
}
