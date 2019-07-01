package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 回文判断 {

    public static boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        int head = 0;
        int tail = chars.length - 1;
        while (head < tail) {
            if (chars[head] != chars[tail]) {
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
