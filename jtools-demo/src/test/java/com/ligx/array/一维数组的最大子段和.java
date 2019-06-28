package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 一维数组的最大子段和 {

    public static int maxSubArray(int[] a) {
        int sum = 0;
        int max = -Integer.MAX_VALUE;
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
        return max;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, -6, 7};
        System.out.println(maxSubArray(a));
    }
}
