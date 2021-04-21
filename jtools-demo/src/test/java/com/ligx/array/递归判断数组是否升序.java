package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月21日.
 */
public class 递归判断数组是否升序 {

    private static boolean isAsc(int[] arr, int pos) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (pos == 1) {
            return true;
        }
        if (arr[pos - 1] > arr[pos]) {
            return false;
        } else {
            return isAsc(arr, pos - 1);
        }
    }

    private static boolean isDesc(int[] arr, int pos) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (pos == 1) {
            return true;
        }
        if (arr[pos - 1] < arr[pos]) {
            return false;
        } else {
            return isDesc(arr, pos - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(isAsc(arr, arr.length - 1));

        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println(isDesc(arr2, arr2.length - 1));
    }
}
