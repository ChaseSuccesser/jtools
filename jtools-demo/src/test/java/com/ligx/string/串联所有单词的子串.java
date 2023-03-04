package com.ligx.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: ligongxing.
 * @date: 2023年03月05日.
 */
public class 串联所有单词的子串 {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;

        // 把字符串数组中的字符串放到HashMap中，key是字符串，value是字符串在数组中出现的次数
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWord = words[0].length();
        int allLen = oneWord * words.length;

        // 滑动窗口，在字符串s中维护着一个所有单词长度总和的窗口
        for (int i = 0; i < s.length() - allLen + 1; i++) {
            // 利用s.substring(i, i + allLen)，取出滑动窗口中的字符串
            String tmp = s.substring(i, i + allLen);

            // 按照单个单词的长度，对滑动窗口字符串进行切割，切割后的单词也放到HashMap中
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j = 0; j < allLen; j += oneWord) {
                String w = tmp.substring(j, j + oneWord);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
            }

            // 如果两个HashMap相同，则认为当前滑动窗口中的字符串满足要求，把当前所以i记录到List中
            if (map.equals(tmp_map)) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> list = findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        System.out.println("list = " + list);
    }
}
