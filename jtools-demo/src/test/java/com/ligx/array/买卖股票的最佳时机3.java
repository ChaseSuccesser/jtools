package com.ligx.array;

import org.junit.Assert;

/**
 * @author: ligongxing.
 * @date: 2023年03月06日.
 */
public class 买卖股票的最佳时机3 {

    private static int maxProfit(int[] prices) {
        int buy1 = prices[0];
        int sell1 = 0;
        int buy2 = prices[0];
        int sell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        return sell2;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        Assert.assertTrue(maxProfit(arr) == 6);
    }
}
