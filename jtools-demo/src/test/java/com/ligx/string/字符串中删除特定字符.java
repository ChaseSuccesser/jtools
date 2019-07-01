package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串中删除特定字符 {

    public static void removeTarget(String str, char c) {
        char[] chars = str.toCharArray();

        int head = 0;
        int tail = 0;
        for (; tail < chars.length; tail++) {
            if (chars[tail] != c) {
                chars[head++] = chars[tail];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < head; i++) {
            sb.append(chars[i]);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        removeTarget(str, 'e');
    }
}
