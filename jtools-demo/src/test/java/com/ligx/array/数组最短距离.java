package com.ligx.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组最短距离 {

    public void minimumDistance(int[] arr) {
        Arrays.sort(arr);

        int item1 = 0;
        int item2 = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] < min) {
                min = arr[i + 1] - arr[i];
                item1 = arr[i];
                item2 = arr[i + 1];
            }
        }

        System.out.printf("item1:%s, item2:%s%n", item1, item2);
    }


    @Test
    public void test() {
        int[] a = {1, 3, 5, 7, 8};
        minimumDistance(a);
    }
}
