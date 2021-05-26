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
        int max, min;
        int i = 0;
        if (arr.length % 2 != 0) {
            max = min = arr[i++];
        } else {
            max = Math.max(arr[0], arr[1]);
            min = Math.min(arr[0], arr[1]);
            i += 2;
        }

        for (int j = i; j < arr.length; j+=2) {
            if (arr[j] > arr[j + 1]) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j + 1]);
            } else {
                max = Math.max(max, arr[j + 1]);
                min = Math.min(min, arr[j]);
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
