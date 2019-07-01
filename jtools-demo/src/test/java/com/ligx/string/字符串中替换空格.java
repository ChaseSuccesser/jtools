package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 字符串中替换空格 {

    public static String replace(String str) {
        // 统计空格的个数
        int blankCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                blankCount++;
            }
        }

        // 用扩容后的长度创建新数组
        int newLen = str.length() + blankCount*2;
        char[] newChars = new char[newLen];

        int oldIndex = str.length() - 1;
        int newIndex = newLen - 1;
        while (oldIndex >= 0) {
            if (str.charAt(oldIndex) == ' ') {
                newChars[newIndex--] = '%';
                newChars[newIndex--] = '0';
                newChars[newIndex--] = '2';
            } else {
                newChars[newIndex--] = str.charAt(oldIndex);
            }
            oldIndex--;
        }

        return new String(newChars);
    }

    public static void main(String[] args) {
        String str = "abc de f";
        System.out.println(replace(str));
    }
}
