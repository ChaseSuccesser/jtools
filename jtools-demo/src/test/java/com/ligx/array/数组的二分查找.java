package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组的二分查找 {

    public static int binarySearch(int[] a, int key) {
        int min = 0;
        int max = a.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (a[mid] == key) {
                return mid;
            } else if (a[mid] > key) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 6, 8};
        System.out.println(binarySearch(a, 6));
    }
}
