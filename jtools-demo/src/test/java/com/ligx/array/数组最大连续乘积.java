package com.ligx.array;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组最大连续乘积 {

    private static int max(int a, int b, int c) {
        int max = Math.max(a, b);
        max = Math.max(max, c);
        return max;
    }

    private static int min(int a, int b, int c) {
        int min = Math.min(a, b);
        min = Math.min(min, c);
        return min;
    }

    private static int arrayMaxContinueProduct(int[] arr) {
        int[] maxArr = new int[arr.length];
        int[] minArr = new int[arr.length];
        maxArr[0] = arr[0];
        minArr[0] = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxArr[i] = max(arr[i], arr[i] * maxArr[i - 1], arr[i] * minArr[i - 1]);
            minArr[i] = min(arr[i], arr[i] * maxArr[i - 1], arr[i] * minArr[i - 1]);
            max = Math.max(max, maxArr[i]);
        }
        return max;
    }


    @Test
    public void test() {
        int[] a = {-3, 4, 0, 3, 2, 8, -1};
        System.out.println(arrayMaxContinueProduct(a));
    }
}
