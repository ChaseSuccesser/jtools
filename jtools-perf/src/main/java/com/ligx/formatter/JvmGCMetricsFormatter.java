package com.ligx.formatter;

import com.ligx.metrics.impl.JvmGCMetrics;
import com.ligx.util.TimeUtil;

import java.util.Date;

/**
 * Author: ligongxing.
 * Date: 2018/10/10.
 */
public class JvmGCMetricsFormatter {

    public String format(JvmGCMetrics metrics, long startMillis, long stopMillis) {
        StringBuilder sb = new StringBuilder(500);

        String dataTitleFormat = "%-19s%19s%19s%19s%n";

        sb.append("JVM GC Metrics [")
                .append(TimeUtil.format(new Date(startMillis), TimeUtil.YMDHMS_FORMAT))
                .append(", ")
                .append(TimeUtil.format(new Date(stopMillis), TimeUtil.YMDHMS_FORMAT))
                .append("]")
                .append(String.format("%n"));
        sb.append(String.format(dataTitleFormat, "YoungGCCount", "YoungGCTime", "FullGCCount", "FullGCTime"));
        if (metrics == null) {
            return sb.toString();
        }

        String dataFormat = "%-19s%19s%19s%19s%n";
        sb.append(String.format(dataFormat,
                metrics.getYoungGCCount(),
                metrics.getYoungGCTime(),
                metrics.getFullGCCount(),
                metrics.getFullGCTime()
        ));
        return sb.toString();
    }

}
