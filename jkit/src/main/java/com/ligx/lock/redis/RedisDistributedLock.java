package com.ligx.lock.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

import java.util.Collections;

/**
 * Author: ligongxing.
 * Date: 2018/11/06.
 */
public class RedisDistributedLock {

    private static final Long RELEASE_SUCCESS = 1L;

    private ThreadLocal<String> lockTag = new ThreadLocal<>();

    private RedisTemplate<String, Object> redisTemplate;

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public boolean tryLock(String lockKey, String value, int millSeconds) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                String result = commands.set(lockKey, value, "NX", "PX", millSeconds);
                return "OK".equals(result);
            }
        });
    }


    public boolean release(String lockKey, String value) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(value));
                return RELEASE_SUCCESS.equals(result);
            }
        });
    }
}
