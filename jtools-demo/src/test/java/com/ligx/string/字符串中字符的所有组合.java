package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串中字符的所有组合 {

    private static void combination(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= chars.length; i++) {
            combination(chars, 0, i, sb);
        }
    }

    private static void combination(char[] chars, int begin, int num, StringBuilder sb) {
        if (num == 0) {
            System.out.println(sb);
            return;
        }
        if (begin == chars.length) {
            return;
        }

        sb.append(chars[begin]);
        combination(chars, begin + 1, num - 1, sb);

        sb.deleteCharAt(sb.length() - 1);
        combination(chars, begin + 1, num, sb);
    }

    public static void main(String[] args) {
        String str = "abcd";
        combination(str.toCharArray());
    }
}
