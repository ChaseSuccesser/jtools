package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 找到字符串中对称子字符串 {

    public static void getLongestSymmetricalLength(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        int symmetricalLen = 1;
        String longestSymmetricalStr = "";
        int i = 0;
        while (i < str.length()) {
            int first = i - 1;
            int end = i + 1;
            while (first >= 0 && end < chars.length && chars[first] == chars[end]) {
                first--;
                end++;
            }
            int newLen = end - first - 1;
            if (newLen > symmetricalLen) {
                longestSymmetricalStr = str.substring(first + 1, end);
                symmetricalLen = newLen;
            }

            first = i;
            end = i + 1;
            while (first >= 0 && end < chars.length && chars[first] == chars[end]) {
                first--;
                end++;
            }
            newLen = end - first - 1;
            if (newLen > symmetricalLen) {
                longestSymmetricalStr = str.substring(first + 1, end);
                symmetricalLen = newLen;
            }

            i++;
        }

        System.out.println("longest symmetrical length: " + symmetricalLen);
        System.out.println("longest symmetrical str: " + longestSymmetricalStr);
    }

    public static void main(String[] args) {
        String str = "aaabcdedcbaff";
        getLongestSymmetricalLength(str);
    }
}
