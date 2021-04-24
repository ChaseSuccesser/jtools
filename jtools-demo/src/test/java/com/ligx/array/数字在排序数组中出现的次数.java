package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 数字在排序数组中出现的次数 {

    public static int findFirstK(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == k) {
                if (mid == 0 || (mid > 0 && arr[mid - 1] != k)) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else if (arr[mid] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int findLastK(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == k) {
                if (mid == arr.length - 1 || (mid < arr.length - 1 && arr[mid + 1] != k)) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            } else if (arr[mid] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 5};
        int firstPos = findFirstK(arr, 4);
        int lastPos = findLastK(arr, 4);
        if (firstPos != -1 && lastPos != -1) {
            System.out.println(lastPos - firstPos + 1);
        } else {
            System.out.println(0);
        }
    }
}
