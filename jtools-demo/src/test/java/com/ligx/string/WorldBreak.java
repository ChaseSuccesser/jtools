package com.ligx.string;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class WorldBreak {

    private static boolean checkWorldBreak(String str, Set<String> dict) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        if (CollectionUtils.isEmpty(dict)) {
            return false;
        }
        boolean[] check = new boolean[str.length() + 1];
        check[0] = true;
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (check[j] && dict.contains(str.substring(j, i))) {
                    check[i] = true;
                    break;
                }
            }
        }
        return check[str.length()];
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
