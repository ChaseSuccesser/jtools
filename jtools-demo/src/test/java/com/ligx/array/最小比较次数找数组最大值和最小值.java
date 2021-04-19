package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月19日.
 */
public class 最小比较次数找数组最大值和最小值 {

    public static void findMaxAndMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int max = 0;
        int min = 0;
        int i = 0;
        if (arr.length % 2 == 0) {
            if (arr[0] > arr[1]) {
                max = arr[0];
                min = arr[1];
            } else {
                max = arr[1];
                min = arr[0];
            }
            i += 2;
        } else {
            max = min = arr[i++];
        }

        for (; i < arr.length; i+=2) {
            if (arr[i] > arr[i + 1]) {
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i + 1]);
            } else {
                max = Math.max(max, arr[i + 1]);
                min = Math.min(min, arr[i]);
            }
        }

        System.out.println("min: " + min);
        System.out.println("max: " + max);
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 6, 2, 7, 8};
        findMaxAndMin(arr);
    }
}
