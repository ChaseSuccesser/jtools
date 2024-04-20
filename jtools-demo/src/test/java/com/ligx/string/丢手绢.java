package com.ligx.string;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2021年04月11日.
 */
public class 丢手绢 {

    public static void diuShouJuan(String[] peoples) {
        int counter = peoples.length;
        int num = 0;

        int[] nums = new int[peoples.length];
        Arrays.fill(nums, 1);

        loop: while (true) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    num += 1;
                    if (num % 3 == 0) {
                        nums[i] = 0;
                        counter--;
                        if (counter == 1) {
                            System.out.println(String.format("编号:%s, %s", i + 1, peoples[i]));
                            break loop;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] peoples = new String[]{"a", "b", "c", "d", "e", "f", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"};
        diuShouJuan(peoples);
    }
}
