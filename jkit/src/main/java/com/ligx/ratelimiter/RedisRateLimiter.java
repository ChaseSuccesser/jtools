package com.ligx.ratelimiter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: ligongxing.
 * Date: 2018/11/06.
 * Usage: 一个limitKey对应一个RedisRateLimiter对象
 */
public class RedisRateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRateLimiter.class);

    private String limitKey;
    private int limitCount;
    private int timeWindow;
    private RedisTemplate<String, Object> redisTemplate;

    private static String luaScript = "";

    static {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("ratelimiter.lua")) {
            luaScript = IOUtils.toString(in, Charset.defaultCharset());
        } catch (Exception e) {
            LOGGER.error("RedisRateLimiter#static initializer,", e);
        }
    }

    /**
     * 构造函数
     *
     * @param limitKey      限流KEY
     * @param limitCount    限流大小
     * @param timeWindow    时间窗口，单位s
     * @param redisTemplate
     */
    public RedisRateLimiter(String limitKey, int limitCount, int timeWindow, RedisTemplate<String, Object> redisTemplate) {
        this.limitKey = limitKey;
        this.limitCount = limitCount;
        this.timeWindow = timeWindow;
        this.redisTemplate = redisTemplate;
    }

    public boolean acquire() {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();

                List<String> keys = new ArrayList<>();
                keys.add(limitKey);

                List<String> args = new ArrayList<>();
                args.add(String.valueOf(limitCount));
                args.add(String.valueOf(timeWindow));

                Long result = (Long) jedis.eval(luaScript, keys, args);
                return result == 1;
            }
        });
    }
}
