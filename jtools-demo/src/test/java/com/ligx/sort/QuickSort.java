package com.ligx.sort;

import java.util.Arrays;

/**
 * @author: ligongxing.
 * @date: 2021年04月07日.
 */
public class QuickSort {

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int temp = a[low];
            int p = low;
            int q = high;

            while (p < q) {
                while (p < q && a[q] >= temp) {
                    q--;
                }
                a[p] = a[q];
                while (p < q && a[p] <= temp) {
                    p++;
                }
                a[q] = a[p];
            }

            a[p] = temp;

            int mid = p;
            quickSort(a, low, mid - 1);
            quickSort(a, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 4, 2, 5, 1, 6};
        quickSort(a, 0, a.length - 1);

        Arrays.stream(a).forEach(System.out::println);
    }
}
