package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 最长公共子串 {

    public static String longestCommonSub(String str1, String str2) {
        int s1Len = str1.length();
        int s2Len = str2.length();

        int[][] matrix = new int[s1Len + 1][s2Len + 1];

        int max = -Integer.MAX_VALUE;
        int x = 0;
        int y = 0;
        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = 0;
                }
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    x = i;
                    y = j;
                }
            }
        }

        char[] longestCommonStr = new char[max];
        x = x - 1;
        y = y - 1;
        while (x >= 0 && y >= 0) {
            if (str1.charAt(x) == str2.charAt(y)) {
                longestCommonStr[--max] = str1.charAt(x);
                x--;
                y--;
            } else {
                break;
            }
        }

        return new String(longestCommonStr);
    }

    public static void main(String[] args) {
        String str1 = "abcdefg";
        String str2 = "ebcdehh";
        System.out.println(longestCommonSub(str1, str2));
    }
}
