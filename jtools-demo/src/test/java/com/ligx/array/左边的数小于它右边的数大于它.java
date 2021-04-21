package com.ligx.array;

import java.util.Arrays;

/**
 * @author: ligongxing.
 * @date: 2021年04月21日.
 */
public class 左边的数小于它右边的数大于它 {

    private static void find(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        Arrays.sort(arr2);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == arr2[i]) {
                System.out.println(arr[i]);
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1, 8, 15, 16};
        find(arr);
    }
}
