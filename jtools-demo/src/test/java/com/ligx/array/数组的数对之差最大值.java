package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组的数对之差最大值 {

    public static int maxDiff(int[] a) {
        int max = a[0];
        int maxDiff = max - a[1];

        for (int i = 2; i < a.length; i++) {
            if (a[i - 1] > max) {
                max = a[i - 1];
            }
            int currentDiff = max - a[i];
            if (currentDiff > maxDiff) {
                maxDiff = currentDiff;
            }
        }

        return maxDiff;
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 1, 16, 7, 5, 11, 9};
        System.out.println(maxDiff(a));
    }
}
