package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年02月28日.
 */
public class JumpGame {

    private static int jump(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j + arr[j] > i) {
                    int temp = dp[j] + i;
                    if (temp < dp[i]) {
                        dp[i] = temp;
                        break;
                    }
                }
            }
        }
        return dp[arr.length - 1];
    }
}
