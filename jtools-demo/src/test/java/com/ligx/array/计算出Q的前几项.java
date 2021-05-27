package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月26日.
 */
public class 计算出Q的前几项 {

    private static void find(int a, int b, int n) {
        int i = 1;
        int j = 1;
        int[] arr = new int[n];
        for (int k = 0; k < n; k++) {
            int tempA = a * i;
            int tempB = b * j;
            if (tempA <= tempB) {
                arr[k] = tempA;
                i++;
            } else {
                arr[k] = tempB;
                j++;
            }
        }
        for (int k = 0; k < n; k++) {
            System.out.print(arr[k] + " ");
        }
    }

    public static void main(String[] args) {
        find(3, 5, 6);
    }
}
