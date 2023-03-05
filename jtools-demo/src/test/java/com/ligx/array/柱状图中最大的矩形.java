package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月05日.
 */
public class 柱状图中最大的矩形 {

    private static int largestRectangleArea(int[] arr) {
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            int left = i;
            int currHeight = arr[i];
            while (left > 0 && arr[left - 1] > currHeight) {
                left--;
            }

            int right = i;
            while (right < arr.length - 1 && arr[right + 1] > currHeight) {
                right++;
            }

            int width = right - left + 1;
            res = Math.max(res, width * arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr));
    }
}
