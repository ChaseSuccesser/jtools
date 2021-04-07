package com.ligx.string;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: ligongxing.
 * @date: 2021年04月07日.
 */
public class 字符串中最长连续数字 {

    public static String continueNumMax(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        char[] chars = str.toCharArray();

        int max = 0;
        int count = 0;
        int start = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ('0' <= c && c <= '9') {
                count++;
            } else {
                if (count > max) {
                    max = count;
                    start = i - max;
                }
                count = 0;
            }
        }

        if (max > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < start + max; i++) {
                sb.append(chars[i]);
            }
            return sb.toString();
        }

        return "";
    }

    public static void main(String[] args) {
        String str = "a1234b12345b";
        System.out.println(continueNumMax(str));
    }
}
