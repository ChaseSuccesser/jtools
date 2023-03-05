package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月05日.
 */
public class 接雨水 {

    private static int trap(int[] height) {
        int sum = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i++) {
            rightMax[i] = Math.max(rightMax[i - 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (min > height[i]) {
                sum += (min - height[i]);
            }
        }

        return sum;
    }
}
