package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 棋盘取数 {

    public static int maxValue(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] valueMatrix = new int[rows][columns];
        valueMatrix[0][0] = matrix[0][0];

        for (int i = 1; i < rows; i++) {
            valueMatrix[i][0] = valueMatrix[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < columns; i++) {
            valueMatrix[0][i] = valueMatrix[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                valueMatrix[i][j] = Math.max(valueMatrix[i - 1][j], valueMatrix[i][j - 1]) + matrix[i][j];
            }
        }
        return valueMatrix[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {2, 4}};
        System.out.println(maxValue(matrix));
    }
}
