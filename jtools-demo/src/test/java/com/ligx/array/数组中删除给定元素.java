package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组中删除给定元素 {

    public static int removeDuplicate(char[] arr, char target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            if (arr[0] == target) {
                return 0;
            } else {
                return 1;
            }
        }
        int head = 0;
        int tail = 0;
        for (; tail < arr.length; tail++) {
            if (arr[tail] != target) {
                arr[head++] = arr[tail];
            }
        }
        return head;
    }

    public static void main(String[] args) {
        char[] a = {'a', 'b', 'b', 'c'};
        int end = removeDuplicate(a, 'b');
        for (int i = 0; i < end; i++) {
            System.out.println(a[i]);
        }
    }
}
