package com.ligx;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组第二大的数 {

    public int findSecond(int[] a) {
        int max = a[0];
        int secMax = -Integer.MAX_VALUE;
        for (int i = 1; i <a.length; i++) {
            if (a[i] > max) {
                secMax = max;
                max = a[i];
            } else {
                if (a[i] > secMax) {
                    secMax = a[i];
                }
            }
        }
        return secMax;
    }


    @Test
    public void test() {
        int[] a = {1, 2, 3, 5};
        System.out.println(findSecond(a));
    }
}
