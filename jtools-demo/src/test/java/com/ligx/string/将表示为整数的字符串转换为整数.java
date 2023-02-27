package com.ligx.string;

/**
 * @author: ligongxing.
 * @date: 2023年02月27日.
 */
public class 将表示为整数的字符串转换为整数 {

    private static void atoi(String str) {
        int num = 0;
        char flag = str.charAt(0);
        int i = 0;

        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            flag = str.charAt(0);
            i++;
        }

        for (int j = i; j < str.length(); j++) {
            char c = str.charAt(j);
            if (c >= '0' && c <= '9') {
                if (flag == '+' && num >= Integer.MAX_VALUE / 10) {
                    num = Integer.MAX_VALUE;
                    break;
                } else if (flag == '-' && num <= Integer.MIN_VALUE / 10) {
                    num = Integer.MIN_VALUE;
                    break;
                }
                num = num * 10 + (c - '0');
            }
        }
        if (flag == '-') {
            num = -num;
        }
        System.out.println(num);
    }

    public static void main(String[] args) {
        atoi("+123456a7");
    }
}
