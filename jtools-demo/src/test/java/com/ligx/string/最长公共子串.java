package com.ligx.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 最长公共子串 {

    public static String longestCommonSub(String str1, String str2) {
        if (StringUtils.isBlank(str1) || StringUtils.isBlank(str2)) {
            return "";
        }
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        int max = 0;
        int x = 0;
        int y = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
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

        if (max > 0) {
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
        return "";
    }

    public static String longestCommonSub2(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];

        int max = Integer.MIN_VALUE;
        int x = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = 0;
                }
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    x = i;
                }
            }
        }

        if (max > 0) {
            return str1.substring(x - max, x);
        }
        return "";
    }

    public static void main(String[] args) {
        String str1 = "abcdefabcdefg";
        String str2 = "ebcdehabcdefh";
        System.out.println(longestCommonSub2(str1, str2));
    }
}
