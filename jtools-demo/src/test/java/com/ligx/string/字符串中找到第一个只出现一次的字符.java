package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串中找到第一个只出现一次的字符 {

    public static char findOne(String str) {
        int[] num = new int[52];

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('a' <= c && c <= 'z') {
                num[c - 'a']++;
            } else if ('A' <= c && c <= 'Z') {
                num[c - 'A' + 26]++;
            }
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('a' <= c && c <= 'z') {
                if (num[c - 'a'] == 1) {
                    return c;
                }
            } else if ('A' <= c && c <= 'Z') {
                if (num[c - 'A' + 26] == 1) {
                    return c;
                }
            }
        }
        return '0';
    }

    public static void main(String[] args) {
        String str = "aabbcdde";
        System.out.println(findOne(str));
    }
}
