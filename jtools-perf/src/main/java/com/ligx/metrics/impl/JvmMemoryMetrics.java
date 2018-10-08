package com.ligx.metrics.impl;

import com.ligx.metrics.Metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public class JvmMemoryMetrics extends Metrics {

    private static final long serialVersionUID = 2158062924684825667L;

    // heap使用内存（eden+survivor+old）
    private long heapUsedMemory;

    private long heapMaxMemory;

    private long oldGenUsedMemory;

    private long oldGenMaxMemory;

    private long edenUsedSpace;

    private long edenMaxSpace;

    private long survivorUsedSpace;

    private long survivorMaxSpace;

    private long metaspaceUsedSpace;

    private long nonHeapUsedMemory;

    private long nonHeapMaxMemory;

    public static JvmMemoryMetrics newInstance() {
        JvmMemoryMetrics jvmMemoryMetrics = new JvmMemoryMetrics();
        jvmMemoryMetrics.calJvmMemory();
        return jvmMemoryMetrics;
    }

    private void calJvmMemory() {
        this.heapUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        this.heapMaxMemory = Runtime.getRuntime().maxMemory();

        MemoryPoolMXBean oldGenMemoryPool = getOldGenMemoryPool();
        if (oldGenMemoryPool != null) {
            this.oldGenUsedMemory = oldGenMemoryPool.getUsage().getUsed();
            this.oldGenMaxMemory = oldGenMemoryPool.getUsage().getMax();
        } else {
            this.oldGenUsedMemory = 0L;
            this.oldGenMaxMemory = 0L;
        }

        MemoryPoolMXBean edenMemoryPool = getEdenMemoryPool();
        if (edenMemoryPool != null) {
            this.edenUsedSpace = edenMemoryPool.getUsage().getUsed();
            this.edenMaxSpace = edenMemoryPool.getUsage().getMax();
        } else {
            this.edenUsedSpace = 0L;
            this.edenMaxSpace = 0L;
        }

        MemoryPoolMXBean survivorMemoryPool = getSurvivorMemoryPool();
        if (survivorMemoryPool != null) {
            this.survivorUsedSpace = survivorMemoryPool.getUsage().getUsed();
            this.survivorMaxSpace = survivorMemoryPool.getUsage().getMax();
        } else {
            this.survivorUsedSpace = 0L;
            this.survivorMaxSpace = 0L;
        }

        MemoryPoolMXBean metaspaceMemoryPool = getMetaspaceMemoryPool();
        if (metaspaceMemoryPool != null) {
            this.metaspaceUsedSpace = metaspaceMemoryPool.getUsage().getUsed();
        } else {
            this.metaspaceUsedSpace = 0L;
        }

        MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        this.nonHeapUsedMemory = nonHeapMemoryUsage.getUsed();
        this.nonHeapMaxMemory = nonHeapMemoryUsage.getMax();
    }

    private MemoryPoolMXBean getOldGenMemoryPool() {
        for (MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().contains("Old Gen")) {
                return memoryPool;
            }
        }
        return null;
    }

    private MemoryPoolMXBean getEdenMemoryPool() {
        for (MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().contains("Eden Space")) {
                return memoryPool;
            }
        }
        return null;
    }

    private MemoryPoolMXBean getSurvivorMemoryPool() {
        for (MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().contains("Survivor Space")) {
                return memoryPool;
            }
        }
        return null;
    }

    private MemoryPoolMXBean getMetaspaceMemoryPool() {
        for (MemoryPoolMXBean memoryPool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (memoryPool.getName().contains("Metaspace")) {
                return memoryPool;
            }
        }
        return null;
    }


    ////////////////////// getter/setter ///////////////////////

    public long getHeapUsedMemory() {
        return heapUsedMemory;
    }

    public void setHeapUsedMemory(long heapUsedMemory) {
        this.heapUsedMemory = heapUsedMemory;
    }

    public long getHeapMaxMemory() {
        return heapMaxMemory;
    }

    public void setHeapMaxMemory(long heapMaxMemory) {
        this.heapMaxMemory = heapMaxMemory;
    }

    public long getOldGenUsedMemory() {
        return oldGenUsedMemory;
    }

    public void setOldGenUsedMemory(long oldGenUsedMemory) {
        this.oldGenUsedMemory = oldGenUsedMemory;
    }

    public long getOldGenMaxMemory() {
        return oldGenMaxMemory;
    }

    public void setOldGenMaxMemory(long oldGenMaxMemory) {
        this.oldGenMaxMemory = oldGenMaxMemory;
    }

    public long getEdenUsedSpace() {
        return edenUsedSpace;
    }

    public void setEdenUsedSpace(long edenUsedSpace) {
        this.edenUsedSpace = edenUsedSpace;
    }

    public long getEdenMaxSpace() {
        return edenMaxSpace;
    }

    public void setEdenMaxSpace(long edenMaxSpace) {
        this.edenMaxSpace = edenMaxSpace;
    }

    public long getSurvivorUsedSpace() {
        return survivorUsedSpace;
    }

    public void setSurvivorUsedSpace(long survivorUsedSpace) {
        this.survivorUsedSpace = survivorUsedSpace;
    }

    public long getSurvivorMaxSpace() {
        return survivorMaxSpace;
    }

    public void setSurvivorMaxSpace(long survivorMaxSpace) {
        this.survivorMaxSpace = survivorMaxSpace;
    }

    public long getMetaspaceUsedSpace() {
        return metaspaceUsedSpace;
    }

    public void setMetaspaceUsedSpace(long metaspaceUsedSpace) {
        this.metaspaceUsedSpace = metaspaceUsedSpace;
    }

    public long getNonHeapUsedMemory() {
        return nonHeapUsedMemory;
    }

    public void setNonHeapUsedMemory(long nonHeapUsedMemory) {
        this.nonHeapUsedMemory = nonHeapUsedMemory;
    }

    public long getNonHeapMaxMemory() {
        return nonHeapMaxMemory;
    }

    public void setNonHeapMaxMemory(long nonHeapMaxMemory) {
        this.nonHeapMaxMemory = nonHeapMaxMemory;
    }
}
