package com.ligx.mongo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: ligongxing.
 * Date: 2018年08月24日.
 */
public class QueryTest {

    @Test
    public void SELECT() {
        Query query = new Query().
                select("a", "b", "c").
                from("t1").
                where(null).
                order_by("d").asc().
                order_by("e").desc().
                skip(5).
                limit(5);

        String str = MongoUtil.findOne(query);
        System.out.println(str);
    }
}