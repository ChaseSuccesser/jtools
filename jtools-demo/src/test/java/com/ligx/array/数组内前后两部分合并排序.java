package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组内前后两部分合并排序 {

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int lBegin = 0;
        int lEnd = arr.length / 2 - 1;
        int rBegin = lEnd + 1;
        int rEnd = arr.length - 1;

        while (lBegin <= lEnd) {
            if (arr[lBegin] > arr[rBegin]) {
                int temp = arr[lBegin];
                arr[lBegin] = arr[rBegin];

                int rIndex = rBegin + 1;
                while (rIndex <= rEnd && temp > arr[rIndex]) {
                    arr[rIndex - 1] = arr[rIndex];
                    rIndex++;
                }
                arr[rIndex - 1] = temp;
            }
            lBegin++;
        }
    }


    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 2, 4, 6, 8};
        mergeSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }
}
