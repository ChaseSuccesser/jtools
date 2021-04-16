package com.ligx.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class 最长连续有效括号的长度 {

    public static int longestValidParentheses(String s) {
        if (StringUtils.isBlank(s)) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        int accumulateLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    accumulateLen = 0;
                } else {
                    int matchedPos = stack.pop();
                    int matchedLen = i - matchedPos + 1;
                    if (stack.isEmpty()) {
                        accumulateLen += matchedLen;
                        matchedLen = accumulateLen;
                    } else {
                        matchedLen = i - stack.peek();
                    }
                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "(()()))()";
        System.out.println(longestValidParentheses(s));
    }
}
