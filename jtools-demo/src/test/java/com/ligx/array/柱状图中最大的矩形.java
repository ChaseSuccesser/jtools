package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月05日.
 */
public class 柱状图中最大的矩形 {

    private static int largestRectangleArea(int[] arr) {
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            int currHeight = arr[i];

            int left = i;
            while (left >= 1 && arr[left - 1] >= currHeight) {
                left = left - 1;
            }

            int right = i;
            while (right < arr.length - 1 && arr[right + 1] >= currHeight) {
                right = right + 1;
            }

            int width = right - left + 1;
            result = Math.max(result, width * currHeight);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr));
    }
}
