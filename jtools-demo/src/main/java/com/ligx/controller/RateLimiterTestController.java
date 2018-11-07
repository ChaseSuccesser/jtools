package com.ligx.controller;

import com.ligx.ratelimiter.RedisRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Author: ligongxing.
 * Date: 2018/11/06.
 */
@RestController
public class RateLimiterTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private RedisRateLimiter redisRateLimiter = null;

    @PostConstruct
    public void init(){
        redisRateLimiter = new RedisRateLimiter("rateLimiterTest", 5, 5, redisTemplate);
    }

    @RequestMapping(value = "/rateLimiterTest", method = RequestMethod.GET)
    public String rateLimiterTest() {
        if (redisRateLimiter.acquire()) {
            return "ok";
        } else {
            return "fail";
        }
    }
}
