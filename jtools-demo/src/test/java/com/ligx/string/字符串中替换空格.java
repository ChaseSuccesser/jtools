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

        int newLength = str.length() + blankCount * 2;
        char[] newChars = new char[newLength];

        int oldP = str.length() - 1;
        int newP = newLength - 1;

        while (oldP >= 0) {
            if (str.charAt(oldP) == ' ') {
                newChars[newP--] = '0';
                newChars[newP--] = '2';
                newChars[newP--] = '%';
            }else {
                newChars[newP--] = str.charAt(oldP);
            }
            oldP--;
        }

        return new String(newChars);
    }

    public static void main(String[] args) {
        String str = "abc de f";
        System.out.println(replace(str));
    }
}
