package com.ligx.string;

/**
 * @author: ligongxing.
 * @date: 2023年03月06日.
 */
public class 不同的子序列 {

    public int numDistinct(String s, String t) {
        final int m = s.length();
        final int n = t.length();

        if (m < n) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }
}
