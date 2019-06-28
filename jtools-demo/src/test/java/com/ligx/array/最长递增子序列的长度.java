package com.ligx.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 最长递增子序列的长度 {

    public int LIS(int[] a) {
        int[] LIS = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i] && LIS[j] + 1 > LIS[i]) {
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        Arrays.sort(LIS);
        return LIS[LIS.length - 1];
    }


    @Test
    public void test() {
        int[] a = {2, 1, 3, 4, 5, 0};
        System.out.println(LIS(a));
    }
}
