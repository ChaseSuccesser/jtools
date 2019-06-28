package com.ligx.array;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组最大连续乘积 {

    private int max(int a, int b, int c) {
        int max = a > b ? a : b;
        max = max > c ? max : c;
        return max;
    }

    private int min(int a, int b, int c) {
        int min = a < b ? a : b;
        min = min < c ? min : c;
        return min;
    }


    public int arrayMaxContinusProduct() {
        int[] a = {-3, 4, 0, 3, 2, 8, -1};

        int[] maxArray = new int[a.length];
        int[] minArray = new int[a.length];
        maxArray[0] = a[0];
        minArray[0] = a[0];
        int maxProduct = a[0];
        for (int i = 1; i < a.length; i++) {
            maxArray[i] = max(a[i], a[i] * maxArray[i - 1], a[i] * minArray[i - 1]);
            minArray[i] = min(a[i], a[i] * maxArray[i - 1], a[i] * minArray[i - 1]);
            if (maxArray[i] > maxProduct) {
                maxProduct = maxArray[i];
            }
        }
        return maxProduct;
    }


    @Test
    public void test() {
        System.out.println(arrayMaxContinusProduct());
    }
}
