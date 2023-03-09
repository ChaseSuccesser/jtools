package com.ligx.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

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
        Assert.assertTrue(isPalindrome("abcba"));
        Assert.assertTrue(isPalindrome("abba"));
        Assert.assertFalse(isPalindrome("abefba"));
    }
}
