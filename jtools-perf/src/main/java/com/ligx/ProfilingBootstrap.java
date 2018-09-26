package com.ligx;

import com.ligx.processor.LoggerMethodMetricProcessor;
import com.ligx.recorder.RecorderMaintainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author: ligongxing.
 * Date: 2018/09/26.
 */
@Component
public class ProfilingBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingBootstrap.class);


    @PostConstruct
    public void init() {
        // todo backupRecordersCount 搞成配置属性
        if (!RecorderMaintainer.getInstance().init(new LoggerMethodMetricProcessor(), 5)) {
            LOGGER.error("ProfilingBootstrap#init, RecorderMaintainer init FAILURE!!!");
            return;
        }
        // todo millTimeSlice 搞成配置属性
        TimeSliceScheduler.initScheduleTask(5000);
    }
}
