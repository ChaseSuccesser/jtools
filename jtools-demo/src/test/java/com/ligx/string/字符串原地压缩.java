package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串原地压缩 {

    public static void compression(String string) {
        int head = 0;
        int tail = 0;
        int count = 0;
        char[] chars = string.toCharArray();
        while (tail < string.length()) {
            count = 0;
            chars[head] = chars[tail];
            while (tail < string.length() && chars[head] == chars[tail]) {
                count++;
                tail++;
            }
            if (count == 1) {
                head++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < head; i++) {
            sb.append(chars[i]);
        }
        System.out.println(sb);
    }


    public static void main(String[] args) {
        String str = "aabbccd";
        compression(str);
    }
}
