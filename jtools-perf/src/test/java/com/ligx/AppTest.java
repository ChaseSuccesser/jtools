package com.ligx;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppTest {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<Integer>() {{
            add(3);
            add(1);
            add(2);
        }};

        Collections.sort(list);

        System.out.println(JSON.toJSON(list));
    }
}
