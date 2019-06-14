package com.ligx;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 和为定值的两个数所有组合 {

    public void qiuhe(int[] a, int m) {
        Arrays.sort(a);

        int head = 0;
        int tail = a.length - 1;
        while (head < tail) {
            if (a[head] + a[tail] == m) {
                System.out.println(a[head] + "+" + a[tail]);
                head++;
                tail--;
            } else if (a[head] + a[tail] > m) {
                tail--;
            } else {
                head++;
            }
        }
    }


    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 6, 7, 8, 9};
        qiuhe(a, 10);
    }
}
