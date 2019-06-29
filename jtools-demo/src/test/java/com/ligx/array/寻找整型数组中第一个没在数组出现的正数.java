package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019年06月29日.
 */
public class 寻找整型数组中第一个没在数组出现的正数 {

    public static int firstMissingPositive(int[] a) {
        int i = 0;
        while (i < a.length) {
            if (a[i] != i + 1 && a[i] >= 1 && a[i] <= a.length && a[a[i] - 1] != a[i]) {
                swap(a, i, a[i] - 1);
            } else {
                i++;
            }
        }
        for (int j = 0; j < a.length; j++) {
            if (a[j] != j + 1) {
                return j + 1;
            }
        }
        return a.length + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(a));
    }
}
