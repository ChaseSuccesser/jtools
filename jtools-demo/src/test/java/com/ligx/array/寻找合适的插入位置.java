package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 寻找合适的插入位置 {

    private static int searchInsertPos(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int head = 0;
        int end = arr.length - 1;
        while (head <= end) {
            int mid = (head + end) / 2;
            if (arr[mid] == m) {
                return mid;
            } else if (arr[mid] > m) {
                end = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        System.out.println(searchInsertPos(arr, 5));
        System.out.println(searchInsertPos(arr, 2));
        System.out.println(searchInsertPos(arr, 7));
        System.out.println(searchInsertPos(arr, 0));

    }
}
