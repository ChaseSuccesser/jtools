package com.ligx.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class WorldBreak {

    public static boolean checkWorldBreak(String str, Set<String> dict) {
        boolean[] check = new boolean[str.length() + 1];
        check[0] = true;

        for (int i = 1; i < str.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (check[j] && dict.contains(str.substring(j, i))) {
                    check[i] = true;
                    break;
                }
            }
        }

        return check[check.length - 1];
    }

    public static void main(String[] args) {
        String str = "abcdef";
        Set<String> dict = new HashSet<String>() {{
            add("abc");
            add("def");
            add("ab");
            add("cdef");
            add("abcde");
        }};

        System.out.println(checkWorldBreak(str, dict));
    }
}
