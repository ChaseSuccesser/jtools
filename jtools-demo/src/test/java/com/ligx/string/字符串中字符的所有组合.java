package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串中字符的所有组合 {

    public static void main(String[] args) {
        String str = "abcd";
        combination(str.toCharArray());
    }

    private static void combination(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= chars.length; i++) {
            doCombination(chars, sb, 0, i);
        }
    }

    private static void doCombination(char[] chars, StringBuilder sb, int begin, int number) {
        if (number == 0) {
            System.out.println(sb);
            return;
        }
        if (begin == chars.length) {
            return;
        }
        sb.append(chars[begin]);
        doCombination(chars, sb, begin + 1, number - 1);
        sb.deleteCharAt(sb.length() - 1);
        doCombination(chars, sb, begin + 1, number);
    }
}
