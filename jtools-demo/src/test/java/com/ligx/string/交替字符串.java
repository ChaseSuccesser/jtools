package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 交替字符串 {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();
        if (s1Len + s2Len != s3Len) {
            return false;
        }

        boolean[][] matrix = new boolean[s1Len + 1][s2Len + 1];
        matrix[0][0] = true;
        for (int i = 1; i <= s1Len; i++) {
            matrix[i][0] = matrix[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= s2Len; i++) {
            matrix[0][i] = matrix[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < s1Len; i++) {
            for (int j = 1; j < s2Len; j++) {
                matrix[i][j] = ((s1.charAt(i - 1) == s3.charAt(i + j - 1)) && matrix[i - 1][j])
                        || ((s2.charAt(j - 1) == s3.charAt(i + j - 1)) && matrix[i][j - 1]);
            }
        }

        return matrix[s1Len][s2Len];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(isInterleave(s1, s2, s3));

        s3 = "aadbbbaccc";
        System.out.println(isInterleave(s1, s2, s3));
    }
}
