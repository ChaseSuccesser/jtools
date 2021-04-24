package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 找出数组中唯一重复的元素 {

    public static int findDuplicate(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        for (int i = 1; i < arr.length; i++) {
            result ^= i;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5};
        System.out.println(findDuplicate(arr));
    }
}