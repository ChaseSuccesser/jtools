package com.ligx;

import com.ligx.recorder.RecorderMaintainer;
import com.ligx.util.ProfilingConf;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/09/26.
 */
public class TimeSliceScheduler {

    private static final ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void initScheduleTask() {
        long millTimeSlice = ProfilingConf.getInstance().getMillTimeSlice();
        scheduledExecutor.scheduleAtFixedRate(() -> {
            long currentMill = System.currentTimeMillis();
            long timeSliceStartMillTime = currentMill - millTimeSlice;

            RecorderMaintainer.getInstance().run(timeSliceStartMillTime, millTimeSlice);
        }, millTimeSlice, millTimeSlice, TimeUnit.MILLISECONDS);
    }

}
