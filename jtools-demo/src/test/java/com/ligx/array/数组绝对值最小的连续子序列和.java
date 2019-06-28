package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组绝对值最小的连续子序列和 {

    public static int getMinAbsoluteSubsequence(int[] a) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (sum * a[i] > 0) {
                sum = a[i];
            } else {
                sum += a[i];
            }
            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
            }
        }
        return Math.abs(min);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, -4};
        System.out.println(getMinAbsoluteSubsequence(a));
    }
}
