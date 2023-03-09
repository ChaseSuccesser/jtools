package com.ligx.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串中删除特定字符 {

    public static String removeTarget(String str, char c) {
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
        return sb.toString();
    }

    public static void main(String[] args) {
        Assert.assertTrue(StringUtils.equals(removeTarget("abcdefg", 'e'), "abcdfg"));
        Assert.assertTrue(StringUtils.equals(removeTarget("abcde", 'e'), "abcd"));
        Assert.assertTrue(StringUtils.equals(removeTarget("abcde", 'a'), "bcde"));
    }
}
