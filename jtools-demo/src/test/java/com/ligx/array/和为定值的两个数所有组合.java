package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 和为定值的两个数所有组合 {


    private static void qiuhe(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int head = 0;
        int tail = arr.length - 1;
        while (head < tail) {
            if (arr[head] + arr[tail] == m) {
                System.out.println(arr[head] + "+" + arr[tail]);
                head++;
                tail--;
            } else if (arr[head] + arr[tail] > m) {
                tail--;
            } else {
                head++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 6, 7, 8, 9};
        qiuhe(a, 10);
    }
}
