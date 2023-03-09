package com.ligx.array;

import org.junit.Assert;

/**
 * @author: ligongxing.
 * @date: 2023年03月06日.
 */
public class 寻找旋转数组中的最小值 {

    private static int searchMin(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < arr[end]) { //todo
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return arr[end];
    }

    public static void main(String[] args) {
        Assert.assertTrue(searchMin(new int[]{3,4,5,1,2}) == 1);
        Assert.assertTrue(searchMin(new int[]{4,5,6,7,0,1,3}) == 0);
        Assert.assertTrue(searchMin(new int[]{4,5,6,1,2,3}) == 1);
    }
}
