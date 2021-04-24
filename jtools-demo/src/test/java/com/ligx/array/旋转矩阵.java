package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 旋转矩阵 {

    public static void rotate(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < columns; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rows - 1 - i][j];
                matrix[rows - 1 - i][j] = temp;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 打印旋转之后的矩阵
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        // 打印原始矩阵
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");

        rotate(matrix);
    }
}
