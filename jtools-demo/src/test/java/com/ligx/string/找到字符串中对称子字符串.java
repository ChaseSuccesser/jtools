package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 找到字符串中对称子字符串 {

    private static void getLongestSymmetricalLength(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        int maxLen = 0;
        String maxStr = "";
        for (int i = 0; i < str.length(); i++) {
            int first = i - 1;
            int end = i + 1;
            while (first >= 0 && end < str.length() && str.charAt(first) == str.charAt(end)) {
                first--;
                end++;
            }
            int newLen = end - first - 1;
            if (newLen > maxLen) {
                maxLen = newLen;
                maxStr = str.substring(first + 1, end);
            }

            first = i;
            end = i + 1;
            while (first >= 0 && end < str.length() && str.charAt(first) == str.charAt(end)) {
                first--;
                end++;
            }
            newLen = end - first - 1;
            if (newLen > maxLen) {
                maxLen = newLen;
                maxStr = str.substring(first + 1, end);
            }
        }

        System.out.println("longest symmetrical length: " + maxLen);
        System.out.println("longest symmetrical str: " + maxStr);
    }

    public static void main(String[] args) {
        String str = "aaabcdedcbaff";
        getLongestSymmetricalLength(str);
    }
}
