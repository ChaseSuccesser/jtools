package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 有序数组删除重复元素只保留一个 {

    public static void removeDuplicate(int[] a) {
        int head = 0;
        int tail = 1;
        for (;tail < a.length; tail++) {
            if(a[head] != a[tail]){
                a[++head] = a[tail];
            }
        }

        for (int i = 0; i < head + 1; i++) {
            if (i == head) {
                System.out.print(a[i]);
            } else {
                System.out.print(a[i] + ",");
            }

        }
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 3, 3, 4, 4, 5};
        removeDuplicate(a);
    }
}
