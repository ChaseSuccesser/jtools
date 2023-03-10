package com.ligx.array;

import org.junit.Assert;

/**
 * Author: ligongxing.
 * Date: 2021年04月26日.
 */
public class 找到两个有序数组的中位数 {

    private static double findMiddle(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(arr1, 0, m - 1, arr2, 0, n - 1, left) + findKth(arr1, 0, m - 1, arr2, 0, n - 1, right)) * 0.5;
    }

    private static int findKth(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        if (len2 < len1) {
            return findKth(arr2, start2, end2, arr1, start1, end1, k);
        }

        if (len1 == 0) {
            return arr2[start2 + k - 1];
        }

        if (k == 1) {
            return Math.min(arr1[start1], arr2[start2]);
        }

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (arr1[i] > arr2[j]) {
            return findKth(arr1, start1, end1, arr2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return findKth(arr1, i + 1, end1, arr2, start2, end2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 8};
        int[] b = {4, 5, 6, 7};
        System.out.println("中位数是:" + findMiddle(a, b));
        Assert.assertTrue(findMiddle(a, b) == 4.5);
    }
}
