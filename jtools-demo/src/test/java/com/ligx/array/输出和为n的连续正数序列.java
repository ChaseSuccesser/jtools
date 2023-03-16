package com.ligx.array;

import java.util.ArrayList;
import java.util.List;

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

    private static int[][] findContinuousSequence2(int n) {
        int small = 1;
        int big = 2;
        int sum = small + big;
        int middle = (1 + n) / 2;
        List<int[]> vec = new ArrayList<>();
        while (small < middle) {
            if (sum == n) {
                vec.add(collect(small, big));
            }
            while (sum > n) {
                sum -= small;
                small++;
                if (sum == n) {
                    vec.add(collect(small, big));
                }
            }
            big++;
            sum += big;
        }
        return vec.toArray(new int[][]{});
    }

    private static int[] collect(int small, int big) {
        int[] arr = new int[big - small + 1];
        int j = 0;
        for (int i = small; i <= big; i++) {
            arr[j++] = i;
        }
        return arr;
    }

    public static void main(String[] args) {
        findContinuousSequence(21);
    }
}
