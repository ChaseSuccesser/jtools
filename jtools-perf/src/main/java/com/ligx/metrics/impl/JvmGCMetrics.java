package com.ligx.metrics.impl;

import com.ligx.metrics.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: ligongxing.
 * Date: 2018/10/10.
 */
public class JvmGCMetrics extends Metrics {

    private static final long serialVersionUID = -1620390226724250207L;

    private static final Logger LOGGER = LoggerFactory.getLogger(JvmGCMetrics.class);

    private long youngGCCount;

    private long youngGCTime;

    private long fullGCCount;

    private long fullGCTime;

    private List<String> youngGenCollectorNames = new ArrayList<String>() {{
        add("copy");        // -XX:+UseSerialGC
        add("ParNew");      // -XX:+UseParNewGC
        add("PS Scavenge"); // -XX:+UseParallelGC
    }};

    private List<String> oldGenCollectorNames = new ArrayList<String>() {{
        add("MarkSweepCompact");    // -XX:+UseSerialGC
        add("PS MarkSweep");        // -XX:+UseParallelGC and (-XX:+UseParallelOldGC or -XX:+UseParallelOldGCCompacting)
        add("ConcurrentMarkSweep"); // -XX:+UseConcMarkSweepGC
    }};

    private JvmGCMetrics() {
    }

    public static JvmGCMetrics newInstance() {
        JvmGCMetrics jvmGCMetrics = new JvmGCMetrics();
        jvmGCMetrics.calJvmGC();
        return jvmGCMetrics;
    }

    private void calJvmGC() {
        List<GarbageCollectorMXBean> garbageCollectorMxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : garbageCollectorMxBeans) {
            String gcName = bean.getName();
            if (youngGenCollectorNames.contains(gcName)) {
                youngGCCount = bean.getCollectionCount();
                youngGCTime = bean.getCollectionTime();
            } else if (oldGenCollectorNames.contains(gcName)) {
                fullGCCount = bean.getCollectionCount();
                fullGCTime = bean.getCollectionTime();
            } else {
                LOGGER.warn("JvmGCMetrics#calJvmGC, Unknown gc name!");
            }
        }
    }

    ////////////////// getter/setter ////////////////

    public long getYoungGCCount() {
        return youngGCCount;
    }

    public void setYoungGCCount(long youngGCCount) {
        this.youngGCCount = youngGCCount;
    }

    public long getYoungGCTime() {
        return youngGCTime;
    }

    public void setYoungGCTime(long youngGCTime) {
        this.youngGCTime = youngGCTime;
    }

    public long getFullGCCount() {
        return fullGCCount;
    }

    public void setFullGCCount(long fullGCCount) {
        this.fullGCCount = fullGCCount;
    }

    public long getFullGCTime() {
        return fullGCTime;
    }

    public void setFullGCTime(long fullGCTime) {
        this.fullGCTime = fullGCTime;
    }
}
