package com.ligx;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 两个数组交集 {

    public int[] jiaoji(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0;
        int j = 0;

        int[] c = new int[a.length + b.length];
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                c[k++] = a[i];
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return c;
    }


    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {3, 4, 5, 6, 7};
        int[] c = jiaoji(a, b);
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }
    }
}
