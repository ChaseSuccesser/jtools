package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 有序数组删除重复元素只保留一个 {

    private static void removeDuplicate(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int head = 0;
        int tail = 1;
        for (; tail < arr.length; tail++) {
            if (arr[head] != arr[tail]) {
                arr[++head] = arr[tail];
            }
        }

        for (int i = 0; i < head + 1; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 3, 3, 4, 4, 5};
        removeDuplicate(a);
    }
}
