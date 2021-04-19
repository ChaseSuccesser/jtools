package com.ligx.array;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组的最长递增子序列LIS {

    // 最长递增子序列的长度
    private static int lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] lis = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        Arrays.sort(lis);
        return lis[arr.length - 1];
    }

    public static void main(String[] args) {
        int[] a = {2, 1, 3, 4, 5, 0};
        System.out.println(lis(a));
    }
}
