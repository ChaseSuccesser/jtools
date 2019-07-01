package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 字符串编辑距离 {

    public static int stringDistance(String str1, String str2) {
        int s1Len = str1.length();
        int s2Len = str2.length();

        int[][] matrix = new int[s1Len + 1][s2Len + 1];

        for (int i = 0; i <= s1Len; i++) {
            matrix[i][0] = i;
        }
        for (int i = 0; i <= s2Len; i++) {
            matrix[0][i] =i;
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = min(matrix[i - 1][j - 1], matrix[i - 1][j], matrix[i][j - 1]) + 1;
                }
            }
        }

        return matrix[s1Len][s2Len];
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
