package com.ligx.redis;

import com.ligx.AbstractSpringJUnit4;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Author: ligongxing.
 * Date: 2018/08/30.
 */
public class RedisTest extends AbstractSpringJUnit4 {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("test", 1);
    }

    @Test
    public void get() {
        redisTemplate.opsForValue().get("test");
    }
}
