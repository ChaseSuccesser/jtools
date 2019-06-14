package com.ligx;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组元素奇偶排序 {

    public void sort(int[] a) {
        int head = 0;
        int tail = a.length - 1;
        while (head < tail) {
            if (!isEven(a[head])) {
                head++;
                continue;
            }
            if (isEven(a[tail])) {
                tail--;
                continue;
            }
            int temp = a[head];
            a[head] = a[tail];
            a[tail] = temp;
        }
    }

    private boolean isEven(int a) {
        return a % 2 == 0;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5, 6};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.err.print(a[i]);
        }
    }
}
