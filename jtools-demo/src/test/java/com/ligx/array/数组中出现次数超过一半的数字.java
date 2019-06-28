package com.ligx.array;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组中出现次数超过一半的数字 {

    public int find(int[] a) {
        int item = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (count == 0) {
                item = a[i];
                count = 1;
            } else {
                if (a[i] == item) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return item;
    }


    @Test
    public void test() {
        System.out.println(find(new int[]{1, 1, 1, 2, 2, 2, 2, 3}));
    }
}
