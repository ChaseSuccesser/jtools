package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组的数对之差最大值 {

    public static int maxDiff(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int max = arr[0];
        int maxDiff = max - arr[1];
        for (int i = 2; i < arr.length; i++) {
            max = Math.max(max, arr[i - 1]);
            maxDiff = Math.max(max - arr[i], maxDiff);
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 1, 16, 7, 5, 11, 9};
        System.out.println(maxDiff(a));
    }
}
