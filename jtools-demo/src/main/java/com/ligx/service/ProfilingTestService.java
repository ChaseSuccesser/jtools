package com.ligx.service;

import com.ligx.base.Profiling;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Author: ligongxing.
 * Date: 2018/09/26.
 */
@Service
public class ProfilingTestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingTestService.class);

    @Profiling
    public String profilingTest() {
        int sleepTime = RandomUtils.nextInt(10, 1000);
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            LOGGER.error("ProfilingTestService#profilingTest,", e);
        }
        return "hello world";
    }


    @Profiling
    public String profilingTestV2() {
        int sleepTime = RandomUtils.nextInt(10, 1000);
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            LOGGER.error("ProfilingTestService#profilingTestV2,", e);
        }
        return "hello world 2";
    }
}
