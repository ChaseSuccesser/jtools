package com.ligx.array;

import org.junit.Assert;

/**
 * @author: ligongxing.
 * @date: 2023年03月14日.
 */
public class 加油站 {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < gas.length; i++) {
            int j = i;
            int remain = gas[i];
            while (remain >= cost[j]) {
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                if (j == i) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        Assert.assertTrue(canCompleteCircuit(gas, cost) == 3);
    }
}
