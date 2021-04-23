package com.ligx.array;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2021年04月23日.
 */
public class 两个数组的交集 {

    public static void inter(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0
                || arr2 == null || arr2.length == 0) {
            return;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int[] arr = new int[Math.min(arr1.length, arr2.length)];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                arr[k] = arr1[i];
                k++;
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        for (int m = 0; m < k; m++) {
            System.out.print(arr[m] + ",");
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 3, 4};
        inter(arr1, arr2);
    }
}
