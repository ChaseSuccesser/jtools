package com.ligx.ratelimiter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Author: ligongxing.
 * Date: 2018/11/06.
 */
public class RedisRateLimiterTest {

    @Test
    public void readLuaScript() throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("ratelimiter.lua");
        String luaScript = IOUtils.toString(in, Charset.defaultCharset());
        System.out.println(luaScript);
    }
}
