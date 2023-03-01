package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年02月28日.
 */
public class n乘n矩阵的最大子矩阵 {

    private static int maxSubMatrix(int[][] matrix) {
        int sum = 0;
        int max = -Integer.MAX_VALUE;

        int[] b = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                b[k] = 0;
            }
            for (int j = i; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    b[k] += matrix[j][k];
                }
                sum = maxSubArray(b);
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum > 0) {
                sum += arr[i];
            } else {
                sum = arr[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
