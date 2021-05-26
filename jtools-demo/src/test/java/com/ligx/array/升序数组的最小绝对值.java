package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月19日.
 */
public class 升序数组的最小绝对值 {


    public static int searchMinAbs(int[] arr, int start, int end) {
        if (start == end) {
            return Math.abs(arr[start]);
        }
        if (end - start + 1 == 2) {
            return Math.min(Math.abs(arr[start]), Math.abs(arr[end]));
        }
        int mid = (start + end) / 2;
        if (arr[mid] >= 0 && arr[mid - 1] <= 0) {
            return Math.min(Math.abs(arr[mid]), Math.abs(arr[mid - 1]));
        }
        if (arr[mid] > 0 && arr[mid - 1] > 0) {
            return searchMinAbs(arr, start, mid);
        }
        if (arr[mid] < 0 && arr[mid - 1] < 0) {
            return searchMinAbs(arr, mid, end);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-4, -3, -2, 1, 2, 3};
        System.out.println(searchMinAbs(arr, 0, arr.length - 1));
    }
}
