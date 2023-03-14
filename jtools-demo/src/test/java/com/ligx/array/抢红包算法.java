package com.ligx.array;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: ligongxing.
 * @date: 2023年03月13日.
 */
public class 抢红包算法 {

    /**
     * @param totalAmount 总金额，单位：分
     * @param peopleCount 总人数
     */
    private void divideRedPackage(int totalAmount, int peopleCount) {
        if (totalAmount < peopleCount) {
            throw new IllegalArgumentException("金额不足");
        }

        List<Integer> amountList = new ArrayList<>();
        int restAmount = totalAmount;
        int restPeopleCount = peopleCount;

        Random random = new Random();
        for (int i = 0; i < peopleCount - 1; i++) {
            // 随机范围：[1，剩余人均金额的2倍) 分
            int amount = random.nextInt(restAmount / restPeopleCount * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleCount--;
            amountList.add(amount);
        }
        amountList.add(restAmount);

        for (int i = 0; i < amountList.size(); i++) {
            System.out.println("抢到金额:" + new BigDecimal(String.valueOf(amountList.get(i))).divide(new BigDecimal("100")));
        }
    }

    @Test
    public void divideRedPackageTest() {
        divideRedPackage(10, 5);
    }
}
