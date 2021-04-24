package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019年06月29日.
 */
public class 荷兰国旗问题 {

    public static void helanguoqi(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int current = 0;
        int begin = 0;
        int end = arr.length - 1;
        while (current <= end) {
            if (arr[current] == 0) {
                swap(arr, current++, begin++);
            } else if (arr[current] == 1) {
                current++;
            } else if (arr[current] == 2) {
                swap(arr, current, end--);
            }
        }
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        int[] a =
                {
                        0, 1, 2,
                        0, 2, 1,
                        1, 0, 2,
                        1, 2, 0,
                        2, 0, 1,
                        2, 1, 0
                };
        helanguoqi(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }
}
