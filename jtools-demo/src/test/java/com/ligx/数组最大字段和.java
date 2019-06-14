package com.ligx;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组最大字段和 {

    public void maxSubArray(int[] a) {
        int sum = 0;
        int max = -1;
        for (int i = 0; i < a.length; i++) {
            if (sum > 0) {
                sum += a[i];
            } else {
                sum = a[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
    }
}
