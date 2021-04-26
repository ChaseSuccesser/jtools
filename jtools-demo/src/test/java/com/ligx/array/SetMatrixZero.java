package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月26日.
 */
public class SetMatrixZero {

    private static void setMatrixZero(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean[] rows = new boolean[arr.length];
        boolean[] columns = new boolean[arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (rows[i] || columns[j]) {
                    arr[i][j] = 0;
                }
            }
        }
    }



    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };
        setMatrixZero(arr);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
