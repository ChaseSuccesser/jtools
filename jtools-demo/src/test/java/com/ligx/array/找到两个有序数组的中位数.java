package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月26日.
 */
public class 找到两个有序数组的中位数 {

    private static int findKth(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int total = m + n;
        if (total % 2 != 0) {
            return findKth(a, m, b, n, total / 2 + 1, 0, 0);
        } else {
            return (findKth(a, m, b, n, total / 2, 0, 0) + findKth(a, m, b, n, total / 2 + 1, 0, 0)) / 2;
        }
    }

    private static int findKth(int[] a, int m, int[] b, int n, int k, int headA, int headB) {
        if (m > n) {
            findKth(b, n, a, m, k, headB, headA);
        }
        if (m == 0) {
            return b[k - 1 + headB];
        }
        if (k == 1) {
            return Math.min(a[headA], b[headB]);
        }
        int pa = Math.min(k / 2, m);
        int pb = k - pa;
        int newPa = headA + (pa - 1);
        int newPb = headB + (pb - 1);
        if (a[newPa] < b[newPb]) {
            return findKth(a, m - pa, b, n, k - pa, headA += pa, headB);
        } else if (a[newPa] > b[newPb]) {
            return findKth(a, m, b, n - pb, k - pb, headA, headB += pb);
        } else {
            return a[newPa];
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6, 7};
        System.out.println("中位数是:" + findKth(a, b));
    }
}
