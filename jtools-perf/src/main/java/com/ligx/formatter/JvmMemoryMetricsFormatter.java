package com.ligx.formatter;


import com.ligx.metrics.impl.JvmMemoryMetrics;
import com.ligx.util.TimeUtil;

import java.util.Date;

/**
 * Author: ligongxing.
 * Date: 2018年10月08日.
 */
public class JvmMemoryMetricsFormatter {

    public String format(JvmMemoryMetrics metrics, long startMillis, long stopMillis) {
        StringBuilder sb = new StringBuilder(500);

        String dataTitleFormat = "%-19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%n";

        sb.append("JVM Memory Metrics [")
                .append(TimeUtil.format(new Date(startMillis), TimeUtil.YMDHMS_FORMAT))
                .append(", ")
                .append(TimeUtil.format(new Date(stopMillis), TimeUtil.YMDHMS_FORMAT))
                .append("]")
                .append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "HeapUsed", "HeapMax", "OldGenUsed", "OldGenMax", "HeapInit", "EdenUsed", "EdenMax", "SurvivorUsed", "SurvivorMax", "MetaspaceUsed", "NonHeapUsed", "NonHeapMax"));
        if (metrics == null) {
            return sb.toString();
        }

        String dataFormat = "%-19d%19d%19d%19d%19d%19d%19d%19d%19d%19d%19d%n";
        sb.append(String.format(dataFormat,
                metrics.getHeapUsedMemory(),
                metrics.getHeapMaxMemory(),
                metrics.getOldGenUsedMemory(),
                metrics.getOldGenMaxMemory(),
                metrics.getEdenUsedSpace(),
                metrics.getEdenMaxSpace(),
                metrics.getSurvivorUsedSpace(),
                metrics.getSurvivorMaxSpace(),
                metrics.getMetaspaceUsedSpace(),
                metrics.getNonHeapUsedMemory(),
                metrics.getNonHeapMaxMemory()
                ));
        return sb.toString();
    }

}
