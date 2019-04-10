package com.ligx.loadbalancer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: ligongxing.
 * Date: 2019/04/10.
 */
public class WeightRandomTest {

    @Test
    public void next() {
        List<Server> serverList = new ArrayList<Server>() {{
            add(new Server("a", 10, 0));
            add(new Server("b", 1, 0));
            add(new Server("c", 1, 0));
        }};

        WeightRandom slb = new WeightRandom(serverList);

        for (int i = 0; i < 20; i++) {
            System.out.println(slb.next());
        }
    }

    @Test
    public void next2() {
        List<Server> serverList = new ArrayList<Server>() {{
            add(new Server("a", 2, 0));
            add(new Server("b", 1, 0));
            add(new Server("c", 1, 0));
        }};

        WeightRandom slb = new WeightRandom(serverList);

        for (int i = 0; i < 4; i++) {
            System.out.println(slb.next());
        }
    }
}