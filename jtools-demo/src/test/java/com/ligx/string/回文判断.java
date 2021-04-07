package com.ligx.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 回文判断 {

    private static boolean isPalindrome(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        int head = 0;
        int tail = str.length() - 1;
        while (head < tail) {
            if (str.charAt(head) != str.charAt(tail)) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("abcba"));
        System.out.println(isPalindrome("abba"));
        System.out.println(isPalindrome("abefba"));
    }
}
