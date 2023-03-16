package com.ligx.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: ligongxing.
 * @date: 2023年03月16日.
 */
public class 翻转字符串2 {

    public String reverseWords(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }


    public static String reverseWordsV2(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.length() != 0) {
                list.add(0, sb.toString());
                sb.setLength(0);
            }
        }

        if (sb.length() != 0) {
            list.add(0, sb.toString());
        }

        return String.join(" ", list);
    }

    public static void main(String[] args) {
        System.out.println(reverseWordsV2("the sky is blue"));
    }
}
