package com.ligx.string;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串中字符的所有组合 {

    public static void combination(char[] string) {
        if (string == null)
            return;
        StringBuilder sb = new StringBuilder();     //用StringBuilder容器来保存组合字符，因为它是动态变化的
        for (int i = 1; i <= string.length; ++i) {  //由于组合可以是1个字符的组合，2个字符的字符…一直到n个字符的组合
            combination(string, 0, i, sb);
        }
    }

    //下面用递归实现
    public static void combination(char[] string, int begin, int number, StringBuilder sb) {
        if (number == 0) {     //递归结束的条件
            System.out.println(sb);
            return;
        }
        if (begin == string.length)
            return;

        sb.append(string[begin]);
        combination(string, begin + 1, number - 1, sb);

        sb.deleteCharAt(sb.length() - 1);
        combination(string, begin + 1, number, sb);
    }


    public static void main(String[] args) {
        String str = "abcd";
        combination(str.toCharArray());
    }
}
