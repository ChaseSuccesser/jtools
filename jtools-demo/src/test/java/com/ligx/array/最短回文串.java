package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月07日.
 */
public class 最短回文串 {

    public String shortestPalindrome(String s) {
        String revS = new StringBuffer(s).reverse().toString();

        for (int i = s.length(); i >= 0; i--) {
            if (s.substring(0, i).equals(revS.substring(s.length() - i))) {
                return revS.substring(0, s.length() - i) + s;
            }
        }
        return "";
    }
}
