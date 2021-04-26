package com.ligx.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ligongxing.
 * @date: 2021年04月26日.
 */
public class 从无序数组中找出最长的连续数字 {

    private static void find(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int next = arr[i] - 1;
            int count = 1;
            while (set.contains(next)) {
                next--;
                count++;
            }
            next = arr[i] + 1;
            while (set.contains(next)) {
                next++;
                count++;
            }
            max = Math.max(count, max);
        }
        System.out.println("max:" + max);
    }

    public static void main(String[] args) {
        int[] arr = {100, 1, 50, 2, 3, 4};
        find(arr);
    }
}
