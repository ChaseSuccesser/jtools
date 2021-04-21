package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月21日.
 */
public class 背包问题 {

    public static int bag(int size, int[] sizeArr, int[] valueArr) {
        if (size == 0 || sizeArr == null || valueArr == null || valueArr.length == 0
                || sizeArr.length != valueArr.length) {
            return 0;
        }
        int[][] fArr = new int[sizeArr.length + 1][size + 1];
        for (int i = 1; i <= sizeArr.length; i++) {
            for (int j = 1; j <= size; j++) {
                if (j >= sizeArr[i-1]) {
                    fArr[i][j] = Math.max(fArr[i - 1][j - sizeArr[i - 1]] + valueArr[i - 1], fArr[i - 1][j]);
                } else {
                    fArr[i][j] = fArr[i - 1][j];
                }
            }
        }
        return fArr[sizeArr.length][size];
    }
}
