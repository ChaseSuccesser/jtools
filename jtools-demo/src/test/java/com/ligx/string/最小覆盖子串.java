package com.ligx.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ligongxing.
 * @date: 2023年03月05日.
 */
public class 最小覆盖子串 {

    private static String minWindow(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            map1.put(t.charAt(i), map1.getOrDefault(t.charAt(i), 0) + 1);
        }

        int l = 0;
        int r = -1;
        int ansL = -1;
        int ansR = -1;
        int minLen = Integer.MAX_VALUE;
        while (r < s.length()) {
            r++;
            if (r < s.length() && map1.containsKey(s.charAt(r))) {
                map2.put(s.charAt(r), map2.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check(map1, map2) && l <= r) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    ansL = l;
                    ansR = r;
                }
                if (map1.containsKey(s.charAt(l))) {
                    map2.put(s.charAt(l), map2.getOrDefault(s.charAt(l), 0) - 1);
                }
                l++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR + 1);
    }

    private static boolean check(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            Character key = entry.getKey();
            Integer count = entry.getValue();
            if (map2.getOrDefault(key, 0) < count) {
                return false;
            }
        }
        return true;
    }
}
