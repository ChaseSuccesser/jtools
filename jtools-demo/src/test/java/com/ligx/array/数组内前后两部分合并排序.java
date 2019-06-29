package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组内前后两部分合并排序 {


    public static void mergeSort(int[] a) {
        int lBegin = 0;
        int lEnd = a.length / 2 - 1;
        int rBegin = lEnd + 1;
        int rEnd = a.length - 1;
        while (lBegin <= lEnd) {
            if (a[lBegin] > a[rBegin]) {
                int temp = a[lBegin];
                a[lBegin] = a[rBegin];
                int next = rBegin + 1;
                while (next <= rEnd && temp > a[next]) {
                    a[next - 1] = a[next];
                    next++;
                }
                a[next - 1] = temp;
            }
            lBegin++;
        }
    }


    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 2, 4, 6, 8};
        mergeSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }
}
