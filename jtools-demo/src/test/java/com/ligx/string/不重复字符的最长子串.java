package com.ligx.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: ligongxing.
 * Date: 2021年04月11日.
 */
public class 不重复字符的最长子串 {

    public static String longestNodupSubstring(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        Map<Character, Integer> cursor = new HashMap<>();
        cursor.put(str.charAt(0), 0);

        int[] lengthAt = new int[str.length()];
        lengthAt[0] = 1;

        int max = 0;

        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (cursor.containsKey(c)) {
                lengthAt[i] = Math.min(lengthAt[i - 1] + 1, i - cursor.get(c));
            } else {
                lengthAt[i] = lengthAt[i - 1] + 1;
            }

            max = Math.max(lengthAt[i], max);
            cursor.put(c, i);
        }

        for (int i = 0; i < lengthAt.length; i++) {
            if (lengthAt[i] == max) {
                return str.substring(i + 1 - max, i + 1);
            }
        }
        return "";
    }

    public static String longestNodupSubstring2(String str) {
        Map<Character, Integer> cursor = new HashMap<>();
        cursor.put(str.charAt(0), 1);

        int[] lengthAt = new int[str.length()];
        lengthAt[0] = 1;

        int max = Integer.MIN_VALUE;
        int x = 0;

        for (int i = 1; i < str.length(); i++) {
            if (cursor.containsKey(str.charAt(i))) {
                lengthAt[i] = Math.min(lengthAt[i - 1] + 1, i - cursor.get(str.charAt(i)));
            } else {
                lengthAt[i] = lengthAt[i - 1] + 1;
            }
            if (lengthAt[i] > max) {
                max = lengthAt[i];
                x = i;
            }
            cursor.put(str.charAt(i), i);
        }
        if (max > 0) {
            return str.substring(x + 1 - max, x + 1);
        }
        return "";
    }

    public static void main(String[] args) {
        String str = "abcdeeaaccabcdef";
        System.out.println(longestNodupSubstring2(str));
    }
}
