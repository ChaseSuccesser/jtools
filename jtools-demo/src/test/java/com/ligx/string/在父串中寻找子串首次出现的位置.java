package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 在父串中寻找子串首次出现的位置 {

    public static int subFirstPos(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i < chars1.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i++;
            }
            if (j == chars2.length) {
                break;
            }
        }
        return i - j;
    }

    public static void main(String[] args) {
        String str1 = "abcdefgcde";
        String str2 = "cde";
        System.out.println(subFirstPos(str1, str2));
    }
}
