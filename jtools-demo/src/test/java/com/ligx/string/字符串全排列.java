package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串全排列 {

    public static void allSort(char[] chars, int start, int end) {
        if (start == end) {
            System.out.println(new String(chars));
            return;
        }
        for (int i = start; i <= end; i++) {
            char temp = chars[start];
            chars[start] = chars[i];
            chars[i] = temp;
            allSort(chars, start + 1, end);
            temp = chars[start];
            chars[start] = chars[i];
            chars[i] = temp;
        }
    }


    public static void main(String[] args) {
        String str = "abc";
        allSort(str.toCharArray(), 0, str.length() - 1);
    }
}
