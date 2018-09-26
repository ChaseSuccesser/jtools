package com.ligx.conf;

import com.ligx.ProfilingBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: ligongxing.
 * Date: 2018/09/26.
 */
@Configuration
public class BeanConf {

    @Bean
    public ProfilingBootstrap profilingBootstrap() {
        return new ProfilingBootstrap();
    }
}
