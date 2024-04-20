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

    public static int numDistinct2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return 0;
        }

        int[][] matrix = new int[sLen + 1][tLen + 1];
        for (int i = 0; i <= sLen; i++) {
            matrix[i][tLen] = 1;
        }

        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = tLen - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    matrix[i][j] = matrix[i + 1][j + 1] + matrix[i + 1][j];
                } else {
                    matrix[i][j] = matrix[i + 1][j];
                }
            }
        }
        return matrix[0][0];
    }

    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        System.out.println(numDistinct2(s, t));
    }
}
