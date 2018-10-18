package com.ligx.http;

import com.ligx.http.httpclient.HttpAgent;
import com.ligx.http.httpclient.HttpConfig;
import org.apache.http.pool.PoolStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: ligongxing.
 * Date: 2018/10/17.
 */
public class HttpManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpManager.class);

    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static Map<String, HttpAgent> httpManager = new ConcurrentHashMap<>();

    private static HttpConfig commonConfig;

    static {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                for (Map.Entry<String, HttpAgent> entry : httpManager.entrySet()) {
                    String name = entry.getKey();
                    HttpAgent httpAgent = entry.getValue();

                    PoolStats poolStats = httpAgent.getConnManager().getTotalStats();
                    // 空闲的持久化连接数目
                    int available = poolStats.getAvailable();
                    // 当前正在执行request的持久化连接的数目. 连接池中全部连接数=available+leased
                    int leased = poolStats.getLeased();
                    // 连接池允许的最大持久化连接数目
                    int max = poolStats.getMax();
                    // 暂未执行等待空闲连接的请求数目
                    int pending = poolStats.getPending();
                    LOGGER.info("http stats: name={}, max={}, leased={}, available={}, pending={}", name, max, leased, available, pending);
                }
            } catch (Exception e) {
                LOGGER.error("HttpManager#static initializer,", e);
            }
        }, 60, 60, TimeUnit.SECONDS);

        //搜索类HTTP配置
        commonConfig = new HttpConfig();
        //连接超时10秒
        commonConfig.setConnectionTimeout(10000); // ms
        //读取数据超时30秒
        commonConfig.setTimeout(30000); // ms
        commonConfig.setMaxConnectionsPerRoute(16);
        commonConfig.setMaxConnections(256);
        commonConfig.setKeepAlive(60000);
        //连接超过5秒未使用时,下次使用前检测是否可用
        commonConfig.setTimeToLive(5);
    }

    private static HttpAgent getHttpAgent(String name, HttpConfig httpConfig) {
        HttpAgent httpAgent = httpManager.get(name);
        if (httpAgent == null) {
            synchronized (HttpManager.class) {
                httpAgent = httpManager.get(name);
                if (httpAgent == null) {
                    httpAgent = HttpAgent.create(httpConfig);
                    httpManager.put(name, httpAgent);
                }
            }
        }
        return httpAgent;
    }

    public static HttpAgent getCommonHttp() {
        return getHttpAgent("common", commonConfig);
    }
}
