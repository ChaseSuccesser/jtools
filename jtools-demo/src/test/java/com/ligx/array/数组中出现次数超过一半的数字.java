package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 数组中出现次数超过一半的数字 {

    private static int find(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int count = 0;
        int item = 0;
        for (int i = 0; i < arr.length; i++) {
            if (count == 0) {
                count = 1;
                item = arr[i];
            } else {
                if (arr[i] == item) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return item;
    }


    public static void main(String[] args) {
        System.out.println(find(new int[]{1, 1, 1, 2, 2, 2, 2, 3}));
    }
}
