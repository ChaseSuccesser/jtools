package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月19日.
 */
public class 最优算法找数组的最大值和最小值 {

    public static void findMaxAndMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (arr.length % 2 == 0) {
            for (int i = 0; i < arr.length; i+=2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        } else {
            for (int i = 0; i < arr.length - 1; i+=2) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        int min = arr[0];
        for (int i = 2; i < arr.length; i+=2) {
            min = Math.min(min, arr[i]);
        }
        int max = arr[1];
        for (int i = 3; i < arr.length; i+=2) {
            max = Math.max(max, arr[i]);
        }

        if (arr.length % 2 != 0) {
            min = Math.min(min, arr[arr.length - 1]);
            max = Math.max(max, arr[arr.length - 1]);
        }

        System.out.println("min: " + min);
        System.out.println("max: " + max);
    }


    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 6, 2, 7, 8};
        findMaxAndMin(arr);
    }
}
