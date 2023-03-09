package com.ligx.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

/**
 * @author: ligongxing.
 * @date: 2023年03月09日.
 */
public class 最短回文串 {

    private static String shortestPalindrome(String s) {
        String revS = new StringBuffer(s).reverse().toString();

        for (int i = s.length(); i > 0; i--) {
            if (s.substring(0, i).equals(revS.substring(s.length() - i))) {
                return revS.substring(0, s.length() - i) + s;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Assert.assertTrue(StringUtils.equals(shortestPalindrome("abcdcbaef"), "feabcdcbaef"));
        Assert.assertTrue(StringUtils.equals(shortestPalindrome("abcde"), "edcbabcde"));
    }
}
