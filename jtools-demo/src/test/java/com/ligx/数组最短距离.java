package com.ligx;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组最短距离 {

    public void MinimumDistance(int[] a) {
        Arrays.sort(a);

        int item1 = 0;
        int item2 = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i + 1] - a[i] < min) {
                min = a[i + 1] - a[i];
                item1 = a[i];
                item2 = a[i + 1];
            }
        }

        System.out.println("item1:"+ item1 + " , item2:" + item2);
    }


    @Test
    public void test() {
        int[] a = {1, 3, 5, 7, 8};
        MinimumDistance(a);
    }
}
