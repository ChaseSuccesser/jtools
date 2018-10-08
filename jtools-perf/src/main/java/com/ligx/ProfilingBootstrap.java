package com.ligx;

import com.ligx.base.Constants;
import com.ligx.base.PropertiesKey;
import com.ligx.recorder.RecorderMaintainer;
import com.ligx.util.ProfilingConf;
import com.ligx.util.ProfilingProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: ligongxing.
 * Date: 2018/09/26.
 */
@Component
public class ProfilingBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingBootstrap.class);


    @PostConstruct
    public void init() {
        if (!initProfilingProperties()) {
            LOGGER.error("ProfilingBootstrap#init, ProfilingProperties init FAILURE!!!");
            return;
        }
        if (!initProfilingConfig()) {
            LOGGER.error("ProfilingBootstrap#init, ProfilingConf init FAILURE!!!");
            return;
        }
        if (!RecorderMaintainer.getInstance().init()) {
            LOGGER.error("ProfilingBootstrap#init, RecorderMaintainer init FAILURE!!!");
            return;
        }
        MethodMetricsScheduler.initScheduleTask();
        JvmMemoryScheduler.initScheduleTask();
    }


    private boolean initProfilingProperties() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_NAME)) {
            if (in != null) {
                Properties prop = new Properties();
                prop.load(in);
                ProfilingProperties.initial(prop);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("ProfilingBootstrap#initProfilingProperties, fileName={}", Constants.PROPERTIES_FILE_NAME, e);
            return false;
        }
    }

    private boolean initProfilingConfig() {
        try {
            ProfilingConf profilingConf = ProfilingConf.getInstance();
            profilingConf.setAppName(ProfilingProperties.getStr(PropertiesKey.APP_NAME, ProfilingConf.DEFAULT_APP_NAME));
            profilingConf.setMostTimeThreshold(ProfilingProperties.getInt(PropertiesKey.MOST_TIME_THRESHOLD, ProfilingConf.DEFAULT_MOST_TIME_THRESHOLD));
            profilingConf.setBackupRecordersCount(ProfilingProperties.getInt(PropertiesKey.BACKUP_RECORDERS_COUNT, ProfilingConf.DEFAULT_BACKUP_RECORDERS_COUNT));
            profilingConf.setMillTimeSlice(ProfilingProperties.getLong(PropertiesKey.MILL_TIME_SLICE, ProfilingConf.DEFAULT_MILL_TIME_SLICE));
            profilingConf.setMethodMetricsProcessor(ProfilingProperties.getStr(PropertiesKey.METHOD_METRICS_PROCESSOR, ProfilingConf.DEFAULT_METHOD_METRICS_PROCESSOR));
            profilingConf.setInfluxdbUrl(ProfilingProperties.getStr(PropertiesKey.INFLUX_DB_URL, null));
            profilingConf.setInfluxdbUserName(ProfilingProperties.getStr(PropertiesKey.INFLUX_DB_USERNAME, null));
            profilingConf.setInfluxdbPassword(ProfilingProperties.getStr(PropertiesKey.INFLUX_DB_PASSWORD, null));
            return true;
        } catch (Exception e) {
            LOGGER.error("ProfilingBootstrap#initProfilingConfig,", e);
            return false;
        }
    }
}
