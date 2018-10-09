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

        String dataTitleFormat = "%-19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%n";

        sb.append("JVM Memory Metrics [")
                .append(TimeUtil.format(new Date(startMillis), TimeUtil.YMDHMS_FORMAT))
                .append(", ")
                .append(TimeUtil.format(new Date(stopMillis), TimeUtil.YMDHMS_FORMAT))
                .append("]")
                .append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "HeapUsed", "HeapMax", "OldGenUsed", "OldGenMax", "EdenUsed", "EdenMax", "SurvivorUsed", "SurvivorMax", "MetaspaceUsed", "MetaspaceMax", "NonHeapUsed", "NonHeapMax"));
        if (metrics == null) {
            return sb.toString();
        }

        String dataFormat = "%-19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%19s%n";
        sb.append(String.format(dataFormat,
                metrics.getHeapUsedMemory() + String.format("(%sMB)", transferToMB(metrics.getHeapUsedMemory())),
                metrics.getHeapMaxMemory() + String.format("(%sMB)", transferToMB(metrics.getHeapMaxMemory())),
                metrics.getOldGenUsedMemory() + String.format("(%sMB)", transferToMB(metrics.getOldGenUsedMemory())),
                metrics.getOldGenMaxMemory() + String.format("(%sMB)", transferToMB(metrics.getOldGenMaxMemory())),
                metrics.getEdenUsedSpace() + String.format("(%sMB)", transferToMB(metrics.getEdenUsedSpace())),
                metrics.getEdenMaxSpace() + String.format("(%sMB)", transferToMB(metrics.getEdenMaxSpace())),
                metrics.getSurvivorUsedSpace() + String.format("(%sMB)", transferToMB(metrics.getSurvivorUsedSpace())),
                metrics.getSurvivorMaxSpace() + String.format("(%sMB)", transferToMB(metrics.getSurvivorMaxSpace())),
                metrics.getMetaspaceUsedSpace() + String.format("(%sMB)", transferToMB(metrics.getMetaspaceUsedSpace())),
                metrics.getMetaspaceMaxSpace() + String.format("(%sMB)", transferToMB(metrics.getMetaspaceMaxSpace())),
                metrics.getNonHeapUsedMemory() + String.format("(%sMB)", transferToMB(metrics.getNonHeapUsedMemory())),
                metrics.getNonHeapMaxMemory() + String.format("(%sMB)", transferToMB(metrics.getNonHeapMaxMemory()))
                ));
        return sb.toString();
    }

    private int transferToMB(long space) {
        if (space <= 0) {
            return (int) space;
        }
        return (int) space / (1024 * 1024);
    }
}
