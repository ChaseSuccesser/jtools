package com.ligx.string;

import org.junit.Assert;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2023年03月07日.
 */
public class 基本计算器 {

    private static int calculate(String s) {
        int sign = 1;
        Stack<Integer> ops = new Stack<>();
        ops.push(sign);

        int result = 0;
        int i = 0;
        while (i < s.length()){
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                result += sign * num;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Assert.assertTrue(calculate("1+2+3-(4+1)") == 1);
        Assert.assertTrue(calculate("1+2+3") == 6);
        Assert.assertTrue(calculate("1+2+3+(4-1)") == 9);
    }

}
