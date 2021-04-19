package com.ligx.array;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组第二大的数 {

    public int findSecondMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int max = arr[0];
        int second = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                second = max;
                max = arr[i];
            } else {
                second = Math.max(second, arr[i]);
            }
        }
        return second;
    }


    @Test
    public void test() {
        int[] a = {1, 2, 3, 5};
        System.out.println(findSecondMax(a));
    }
}
