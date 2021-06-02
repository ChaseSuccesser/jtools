package com.ligx.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 字符串编辑距离 {

    private static int stringDistance(String str1, String str2) {
        if (StringUtils.isBlank(str1) || StringUtils.isBlank(str2)) {
            return 0;
        }
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            matrix[i][0] = i;
        }
        for (int i = 0; i <= str2.length(); i++) {
            matrix[0][i] = i;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = min(matrix[i - 1][j - 1], matrix[i - 1][j], matrix[i][j - 1]) + 1;
                }
            }
        }
        return matrix[str1.length()][str2.length()];
    }

    private static int min(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "abddf";
        System.out.println("min distance:" + stringDistance(str1 , str2));
    }
}
