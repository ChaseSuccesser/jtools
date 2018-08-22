package com.ligx.http;

import com.ligx.http.httpclient.HttpAgent;
import org.junit.Test;

public class HttpAgentTest {

    @Test
    public void doGet() throws Exception {
        HttpAgent agent = HttpAgent.create();
        String result = agent.doGet("http://www.baidu.com");
        System.out.println(result);
    }
}