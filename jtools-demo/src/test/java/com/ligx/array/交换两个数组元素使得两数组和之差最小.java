package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 交换两个数组元素使得两数组和之差最小 {

    private static void balanceArray(int[] arr1, int[] arr2) {
        int[] arr = new int[0];

        if (sum(arr1) < sum(arr2)) {
            arr = arr1;
            arr1 = arr2;
            arr2 = arr;
        }

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int x = arr1[i] - arr2[j];
                int A = sum(arr1) - sum(arr2);
                if (0 < x && x < A) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 4, 1, 1};
        int[] arr2 = {2, 3, 4, 8, 9};
        balanceArray(arr1, arr2);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }
}
