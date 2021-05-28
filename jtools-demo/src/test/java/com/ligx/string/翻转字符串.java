package com.ligx.string;

/**
 * @author: ligongxing.
 * @date: 2021年04月07日.
 */
public class 翻转字符串 {

    private static void reverse(char[] arr, int begin, int end) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = begin, j = end; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static void reverseShift(char[] arr, int k) {
        if (arr == null || arr.length == 0  || k <= 0 || arr.length < k) {
            return;
        }
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        String str = "abcdef";
        char[] a = str.toCharArray();

        reverseShift(a, 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }
}
