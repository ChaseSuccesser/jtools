package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 字符串中替换空格 {

    private static String replace(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int blankCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                blankCount++;
            }
        }
        int newLen = str.length() + blankCount * 2;
        char[] chars = new char[newLen];

        int newIndex = chars.length - 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != ' ') {
                chars[newIndex--] = str.charAt(i);
            } else {
                chars[newIndex--] = '0';
                chars[newIndex--] = '2';
                chars[newIndex--] = '%';
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String str = "abc de f";
        System.out.println(replace(str));
    }
}
