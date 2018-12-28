package com.ligx.loadbalancer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: ligongxing.
 * Date: 2018/12/28.
 */
public class WeightRoundRobinTest {

    @Test
    public void next() {
        List<Server> serverList = new ArrayList<Server>() {{
            add(new Server("a", 10, 0));
            add(new Server("b", 1, 0));
            add(new Server("c", 1, 0));
        }};

        WeightRoundRobin slb = new WeightRoundRobin(serverList);

        for (int i = 0; i < 10; i++) {
            System.out.println(slb.next());
        }
    }
}