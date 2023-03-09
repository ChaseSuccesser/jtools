package com.ligx.string;

import java.util.BitSet;

/**
 * @author: ligongxing.
 * @date: 2023年03月09日.
 */
public class 字符串是否包含 {

    private static boolean checkContains(String s, String p) {
        BitSet bitSet = new BitSet();

        for (int i = 0; i < s.length(); i++) {
            bitSet.set(s.charAt(i));
        }

        for (int i = 0; i < p.length(); i++) {
            if (!bitSet.get(p.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String p = "cbaed";
        System.out.println(checkContains(s, p));
    }
}
