package com.ligx.array;

import java.util.Arrays;

/**
 * @author: ligongxing.
 * @date: 2023年03月07日.
 */
public class 地下城游戏 {

    public static int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[n - 1][m] = dp[n][m - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }


    public static void main(String[] args) {
        int[][] arr = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(calculateMinimumHP(arr));
    }
}
