package com.ligx.lock;

import com.ligx.AbstractSpringJUnit4;
import com.ligx.lock.redis.RedisDistributedLock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Author: ligongxing.
 * Date: 2018/11/06.
 */
public class RedisDistributedLockTest extends AbstractSpringJUnit4 {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String lockKey = "test";
    private String value = "2333333333";

    @Test
    public void tryLock() {
        boolean result = new RedisDistributedLock(redisTemplate).tryLock(lockKey, value, 100000);
        if (result) {
            System.out.println("加锁成功!");
        } else {
            System.out.println("加锁失败!");
        }
    }

    @Test
    public void release() {
        boolean result = new RedisDistributedLock(redisTemplate).release(lockKey, value);
        if (result) {
            System.out.println("释放锁成功!");
        } else {
            System.out.println("释放锁失败!");
        }
    }
}
