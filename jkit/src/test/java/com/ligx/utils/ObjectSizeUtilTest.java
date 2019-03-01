package com.ligx.utils;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: ligongxing.
 * Date: 2019/03/01.
 */
public class ObjectSizeUtilTest {

    @Test
    public void objectSize() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", new Date());
        map.put("c", "");

        ObjectSizeUtil.objectSize(map);
    }


    @Test
    public void objectSize2() {
        Person p = new Person("lgx", 26, 1111L);
        ObjectSizeUtil.objectSize(p);
    }

    @Test
    public void objectSize3() {
        Person p = new Person();
        ObjectSizeUtil.objectSize(p);
    }

}

class Person {
    private String name;

    private int age;

    private long weight;

    public Person() {
    }

    public Person(String name, int age, long weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
}