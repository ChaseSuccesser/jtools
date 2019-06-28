package com.ligx.array;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 最长公共子序列 {

    public void comSubstring(char[] a, char[] b) {
        int a_length = a.length;
        int b_length = b.length;
        int[][] lcs = new int[a_length + 1][b_length + 1];
        // 初始化二维数组，每个位置的值为0
        for (int i = 0; i <= b_length; i++) {
            for (int j = 0; j <= a_length; j++) {
                lcs[j][i] = 0;
            }
        }
        //为每个数组位置赋值
        for (int i = 1; i <= a_length; i++) {
            for (int j = 1; j <= b_length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                }
                if (a[i - 1] != b[j - 1]) {
                    lcs[i][j] = lcs[i][j - 1] > lcs[i - 1][j] ? lcs[i][j - 1] : lcs[i - 1][j];
                }
            }
        }
        // 由该二维数组构造最小公共子序列
        int max_length = lcs[a_length][b_length];    //最右下角是最大的数，最大的数便是 X 和 Y 的最长公共子序列的长度
        char[] comStr = new char[max_length];      //定义一个一维数组保存最长公共子序列
        int i = a_length, j = b_length;
        while (max_length > 0) {
            if (lcs[i][j] != lcs[i - 1][j - 1]) {      //当前二维数组元素与左上对角线的数组元素不相等；并且当前二维数组位置的上边元素和左
                if (lcs[i - 1][j] == lcs[i][j - 1]) {  //边元素相等，表示当前数组位置所对应的两字符相等，为公共字符，可以构造成最长公共子序列
                    comStr[max_length - 1] = a[i - 1];
                    max_length--;
                    i--;
                    j--;
                } else if (lcs[i - 1][j] > lcs[i][j - 1]) {   //取两者中较长者作为A和B的最长公共子序列
                    i--;
                } else {
                    j--;
                }
            } else {    //当前二维数组元素与左上对角线的数组元素相等，表明当前数组位置所对应的两个字符不想等，则不能作为最长公共子序列
                i--;
                j--;
            }
        }
        System.out.print("最长公共字符串是：");
        System.out.print(comStr);
    }


    public void comSubstring2(char[] a, char[] b) {
        int aLen = a.length;
        int bLen = b.length;
        int[][] matrix = new int[aLen+1][bLen+1];
        for (int i = 0; i <= aLen; i++) {
            for (int j = 0; j <= bLen; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (a[i - 1] == b[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j]>matrix[i][j - 1] ? matrix[i - 1][j] : matrix[i][j - 1];
                }
            }
        }
        int count = matrix[aLen][bLen];
        char[] c = new char[count];
        int i = aLen;
        int j = bLen;
        while (count > 0) {
            if (matrix[i][j] != matrix[i - 1][j - 1]) {
                if (matrix[i][j - 1] == matrix[i - 1][j]) {
                    c[count - 1] = a[i-1];
                    count--;
                    i--;
                    j--;
                } else if (matrix[i][j - 1] < matrix[i - 1][j]) {
                    i--;
                } else {
                    j--;
                }
            } else {
                i--;
                j--;
            }
        }
        System.out.print("最长公共字符串是：");
        System.out.print(c);
    }


    @Test
    public void test() {
        char[] a = {'a', 'b', 'c', 'd'};
        char[] b = {'a', 'e', 'c', 'd'};
        comSubstring2(a, b);
    }
}
