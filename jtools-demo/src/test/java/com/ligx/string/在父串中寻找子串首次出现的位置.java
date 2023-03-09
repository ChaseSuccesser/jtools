package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 在父串中寻找子串首次出现的位置 {

    public static void main(String[] args) {
        String str1 = "abcdefgcde";
        String str2 = "def";
        System.out.println(subFirstPos(str1, str2));
    }

    private static int subFirstPos(String str1, String str2) {
        int i = 0;
        int j = 0;

        while (i < str1.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
                j = 0;
            }
            if (j == str2.length()) {
                return i - j;
            }
        }
        return -1;
    }
}
