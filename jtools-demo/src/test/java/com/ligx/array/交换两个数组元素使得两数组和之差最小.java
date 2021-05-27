package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 交换两个数组元素使得两数组和之差最小 {

    private static void balanceArray2(int[] arr1, int[] arr2) {
        int[] tempArr;
        if (sum(arr1) < sum(arr2)) {
            tempArr = arr1;
            arr1 = arr2;
            arr2 = tempArr;
        }

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int x = arr1[i] - arr2[j];
                int A = sum(arr1) - sum(arr2);
                if (0 < x && x < A) {
                    int temp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = temp;
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

        System.out.println(String.format("arr1 sum:%s", sum(arr1)));
        System.out.println(String.format("arr2 sum:%s", sum(arr2)));

        balanceArray2(arr1, arr2);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        System.out.println(String.format("arr1 sum:%s", sum(arr1)));
        System.out.println(String.format("arr2 sum:%s", sum(arr2)));
    }
}
