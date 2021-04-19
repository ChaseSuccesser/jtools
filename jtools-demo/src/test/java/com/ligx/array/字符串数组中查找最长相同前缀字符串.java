package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019年06月29日.
 */
public class 字符串数组中查找最长相同前缀字符串 {

    private static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = strs[0].length();
        String minStr = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                minStr = strs[i];
            }
        }

        for (int i = 0; i < minStr.length(); i++) {
            char c = minStr.charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return minStr.substring(0, i);
                }
            }
        }
        return minStr;
    }

    public static void main(String[] args) {
        String[] strs = {"abcf", "abcee", "abcd"};
        System.out.println(longestCommonPrefix(strs));
    }
}
