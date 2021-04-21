package com.ligx.array;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组元素奇偶排序 {

    public void oddAndEvenSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            if (!isEven(arr[start])) {
                start++;
                continue;
            }
            if (isEven(arr[end])) {
                end--;
                continue;
            }
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }

    private boolean isEven(int a) {
        return a % 2 == 0;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5, 6};
        oddAndEvenSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }
}
