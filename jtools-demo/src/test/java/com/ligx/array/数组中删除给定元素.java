package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组中删除给定元素 {

    public static void removeDuplicate(char[] a, char target) {
        int head = 0;
        int tail = 0;
        for (; tail < a.length; tail++) {
            if (a[tail] != target) {
                a[head++] = a[tail];
            }
        }

        for (int i = 0; i < head; i++) {
            System.out.print(a[i]);
        }
    }

    public static void main(String[] args) {
        char[] a = {'a', 'b', 'b', 'c'};
        removeDuplicate(a, 'b');
    }
}
