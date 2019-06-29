package com.ligx.array;

/**
 * Author: ligongxing.
 * Date: 2019年06月29日.
 */
public class 字符串数组中查找最长相同前缀字符串 {

    public static String longestCommonPrefix(String[] strs) {
        int minStrLen = strs[0].length();
        String minStr = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minStrLen) {
                minStrLen = strs[i].length();
                minStr = strs[i];
            }
        }
        char[] chars = minStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return minStr.substring(0, i);
                }
            }
        }
        return minStr;
    }

    public static void main(String[] args) {
        String[] strs = {"ab", "abc", "abcd"};
        System.out.println(longestCommonPrefix(strs));
    }
}
