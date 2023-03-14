package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月14日.
 */
public class 剪绳子 {

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int currMax = Integer.MIN_VALUE;
            for (int j = 1; j < i; j++) {
                currMax = Math.max(currMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = currMax;
        }
        return dp[n];
    }
}
