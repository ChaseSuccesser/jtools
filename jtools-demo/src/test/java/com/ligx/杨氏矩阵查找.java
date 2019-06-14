package com.ligx;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 杨氏矩阵查找 {

    public int find(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int row = 0;
        int column = columns - 1;

        while (row < rows && column <= 0) {
            if (matrix[row][column] == target) {
                return matrix[row][column];
            } else if (target < matrix[row][column]) {
                column -= 1;
            } else {
                row += 1;
            }
        }
        return -1;
    }
}
