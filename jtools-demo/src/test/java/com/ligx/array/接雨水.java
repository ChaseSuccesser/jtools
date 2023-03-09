package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月05日.
 */
public class 接雨水 {


    private static int test(int[] arr) {
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i - 1]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i + 1]);
        }

        int sum = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (min > arr[i]) {
                sum += (min - arr[i]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(test(arr));;
    }
}
