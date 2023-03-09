package com.ligx.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串原地压缩 {

    public static void main(String[] args) {
        String str = "aabbccd";
        System.out.println(compression(str));
        Assert.assertTrue(StringUtils.equals(compression(str), "d"));
    }

    private static String compression(String str) {
        char[] chars = str.toCharArray();

        int head = 0;
        int tail = 0;

        int count;
        while (tail < str.length()) {
            count = 0;
            chars[head] = chars[tail];

            while (tail < str.length() && chars[head] == chars[tail]) {
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
        return sb.toString();
    }
}
