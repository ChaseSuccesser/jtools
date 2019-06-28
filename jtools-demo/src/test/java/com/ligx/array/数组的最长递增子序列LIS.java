package com.ligx.array;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组的最长递增子序列LIS {

    // 最长递增子序列的长度
    public static int lis(int[] a) {
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
        return LIS[a.length - 1];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 3, 4, 5, 0};
        System.out.println(lis(a));
    }
}
