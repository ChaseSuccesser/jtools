package com.ligx.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 把数组排成最小的数 {

    private static void min(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Integer[] arr1 = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        Arrays.sort(arr1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ("" + o1 + o2).compareTo("" + o2 + o1);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr1.length; i++) {
            sb.append(arr1[i]);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        int[] arr = {32, 321};
        min(arr);

        int[] arr2 = {4, 3, 2, 1};
        min(arr2);
    }
}
