package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月19日.
 */
public class 容器里最多能放多少水 {

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        while (start < end) {
            int area = (end - start) * Math.min(height[start], height[end]);
            max = Math.max(max, area);

            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }
}
