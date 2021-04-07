package com.ligx.string;

import java.util.Arrays;

/**
 * @author: ligongxing.
 * @date: 2021年04月07日.
 */
public class 翻转字符串 {

    private static void reverse(char[] a, int begin, int end) {
        for (int i = begin, j = end; i < j; i++, j--) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    private static void reverseShift(char[] a, int length) {
        if (a == null || a.length == 0 || length <= 0) {
            return;
        }
        int begin = 0;
        int end = a.length - 1;
        reverse(a, begin, length - 1);
        reverse(a, length, end - 1);
        reverse(a, begin, end - 1);
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
