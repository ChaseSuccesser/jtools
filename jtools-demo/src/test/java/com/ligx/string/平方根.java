package com.ligx.string;

import org.junit.Assert;

/**
 * @author: ligongxing.
 * @date: 2023年03月14日.
 */
public class 平方根 {

    private static int sqrt(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid == n / mid) {
                return mid;
            } else if (mid < n / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        Assert.assertTrue(sqrt(9) == 3);
        Assert.assertTrue(sqrt(16) == 4);
        Assert.assertTrue(sqrt(25) == 5);
        Assert.assertTrue(sqrt(36) == 6);
        Assert.assertTrue(sqrt(49) == 7);
    }
}
