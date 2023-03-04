package com.ligx.string;

/**
 * @author: ligongxing.
 * @date: 2023年03月04日.
 */
public class 正则表达式匹配 {


    private static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        final int sLen = s.length();
        final int pLen = p.length();

        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        // s、p 都为空串，肯定匹配
        dp[0][0] = true;

        // s不为空串，p为空串，肯定不匹配。因为boolean数组默认值为false，所以无需处理

        // s为空串，p不为空串，要想匹配，只可能是右端是星号，它干掉一个字符后，把 p 变为空串。
        for (int i = 1; i < pLen + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                // 情况1：s[i−1]与p[j-1]是匹配的,
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    // 那么就需要考查s(0,i-2)与p(0,j-2)
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 情况2：s[i−1]与p[j-1]不匹配，但是p[j-1]为星号，例如s(_ _ _ _) p(_ _ _ _ _ *)
                else if (p.charAt(j - 1) == '*') {
                    // 情况2.1：s[i−1]与p[j-2]匹配，例如s(_ _ _ a) p(_ _ _ _ a *)
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j - 2]  //*让p[j-2]重复0次，也就是干掉p[j-2]和p[j-1]，考查s(0,i-1)与p(0,j-3)
                                || dp[i - 1][j - 2] //*让p[j-2]重复1次，即和s[i-1]匹配抵消，也就是干掉p里面的p[j-2]和p[j-1]，干掉s里面的s[i-1]，考查s(0,i-2)与p(0,j-3)
                                || dp[i - 1][j]; //*让p[j-2]重复>=2次，我们从p中虚拟拿出一个p[j-2]和s[i-1]匹配抵消，也就是干掉s里面的s[i-1]，p里面不干掉任何元素，考查s(0,i-2)与p(0,j-1)
                    } else {
                        // 情况2.2：s[i−1]与p[j-2]也不匹配，例如s(_ _ _ a) p(_ _ _ _ b *)
                        // 这时候就需要用*干掉p[j-2]和p[j-1]，考查s(0,i-1)和p(0,j-3)
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("abc", "ab*c"));
    }
}
