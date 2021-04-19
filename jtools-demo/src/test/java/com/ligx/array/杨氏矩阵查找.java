package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 杨氏矩阵查找 {

    public int find(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;

        int row = 0;
        int column = columns - 1;
        while (row < rows && column >= 0) {
            if (target == matrix[row][column]) {
                return matrix[row][column];
            } else if (target < matrix[row][column]) {
                column = column - 1;
            } else {
                row = row + 1;
            }
        }
        return -1;
    }
}
