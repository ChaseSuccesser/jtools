package com.ligx.array;

import org.junit.Assert;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 杨氏矩阵查找 {

    private static int find(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int row = 0;
        int column = columns - 1;

        while (row < rows && column >= 0) {
            if (matrix[row][column] == target) {
                return matrix[row][column];
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};
        Assert.assertTrue(find(matrix, 4) == 4);
        Assert.assertTrue(find(matrix, 6) == 6);
    }
}
