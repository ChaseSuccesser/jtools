package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月06日.
 */
public class 买卖股票的最佳时机3 {

    public int maxProfit(int[] prices) {
        int buy1 = prices[0], sell1 = 0;
        int buy2 = prices[0], sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        return sell2;
    }
}
