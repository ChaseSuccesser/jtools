package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组最长公共子序列LCS {

    private static void comSubString(char[] a, char[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[][] matrix = new int[aLen + 1][bLen + 1];

        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a[i - 1] == b[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }

        int maxCommonSubStrLen = matrix[aLen][bLen];
        char[] chars = new char[maxCommonSubStrLen];
        int i = aLen;
        int j = bLen;
        while (maxCommonSubStrLen > 0) {
            if (matrix[i][j] != matrix[i - 1][j - 1]) {
                if (matrix[i - 1][j] == matrix[i][j - 1]) {
                    chars[--maxCommonSubStrLen] = a[i - 1];
                    i--;
                    j--;
                } else if (matrix[i - 1][j] > matrix[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            } else {
                i--;
                j--;
            }
        }
        System.out.print("最长公共子序列是：");
        System.out.print(chars);
    }

    public static void main(String[] args) {
        char[] a = {'a', 'b', 'c', 'd'};
        char[] b = {'a', 'e', 'c', 'd'};
        comSubString(a, b);
    }
}
