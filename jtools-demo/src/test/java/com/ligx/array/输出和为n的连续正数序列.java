package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2021年04月26日.
 */
public class 输出和为n的连续正数序列 {

    private static void findContinuousSequence(int n) {
        if (n < 3) {
            return;
        }
        int small = 1;
        int big = 2;
        int sum = small + big;
        int middle = (1 + n) / 2;
        while (small < middle) {
            if (sum == n) {
                print(small, big);
            }
            while (sum > n) {
                sum -= small;
                small++;
                if (sum == n) {
                    print(small, big);
                }
            }
            big++;
            sum += big;
        }
    }

    private static void print(int small, int big) {
        for (int i = small; i <= big; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        findContinuousSequence(21);
    }
}
