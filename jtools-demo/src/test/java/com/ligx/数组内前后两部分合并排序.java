package com.ligx;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组内前后两部分合并排序 {

    public void sort(int[] a) {
        int lBegin = 0, lEnd = a.length / 2;
        int rBegin = lEnd + 1, rEnd = a.length - 1;

        while (lBegin <= lEnd) {
            if (a[lBegin] > a[rBegin]) {
                int temp = a[lBegin];
                a[lBegin] = a[rBegin];

                int next = rBegin + 1;
                while (next <= rEnd && temp > a[next]) {
                    a[next--] = a[next];
                    next++;
                }
                a[next - 1] = temp;
            }
            lBegin++;
        }
    }
}
