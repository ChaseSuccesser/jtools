package com.ligx.util;

import com.ligx.base.Constants;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: ligongxing.
 * Date: 2018/09/28.
 */
public class DbUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbUtil.class);

    private static volatile InfluxDB influxDBClient;


    private static InfluxDB getInfluxDBClient(){
        if (influxDBClient == null) {
            synchronized (DbUtil.class) {
                if (influxDBClient == null) {
                    String url = ProfilingConf.getInstance().getInfluxdbUrl();
                    String username = ProfilingConf.getInstance().getInfluxdbUserName();
                    String password = ProfilingConf.getInstance().getInfluxdbPassword();
                    if (StringUtils.isNotBlank(url)) {
                        if (StringUtils.isBlank(username)) {
                            influxDBClient = InfluxDBFactory.connect(url);
                        } else {
                            influxDBClient = InfluxDBFactory.connect(url, username, password);
                        }
                        LOGGER.info("DbUtil#getInfluxDBClient, influxdb init complete!!! url={}, username={}, password={}",
                                url, username, password);
                    } else {
                        LOGGER.error("DbUtil#getInfluxDBClient, influxdb url is empty!!!");
                    }
                }
            }
        }
        return influxDBClient;
    }

    public static void write(String lineProtocol) {
        try {
            if (getInfluxDBClient() == null) {
                LOGGER.error("DbUtil#write, influxdb client is null!!!");
                return;
            }
            getInfluxDBClient().setDatabase(Constants.INFLUX_DB_NAME).write(lineProtocol);
        } catch (Exception e) {
            LOGGER.error("DbUtil#write, lineProtocol={}", lineProtocol, e);
        }
    }
}
