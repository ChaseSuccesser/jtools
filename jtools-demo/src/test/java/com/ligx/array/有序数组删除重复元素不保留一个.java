package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 有序数组删除重复元素不保留一个 {

    public static void removeDuplicate(char[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int head = 0;
        int tail = 0;
        int count;
        while (tail < a.length) {
            count = 0;
            a[head] = a[tail];
            while (tail < a.length && a[head] == a[tail]) {
                count++;
                tail++;
            }
            if (count == 1) {
                head++;
            }
        }
        for (int i = 0; i < head; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static void main(String[] args) {
        char[] a = {'1', '1', '1', '2', '3', '3', '4', '5', '5'};
        removeDuplicate(a);
    }
}
