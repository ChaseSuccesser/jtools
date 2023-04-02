package com.ligx.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class 生成括号 {

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(n, n, "", list);
        return list;
    }

    private static void generate(int left, int right, String str, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left > 0) {
            generate(left - 1, right, str + "(", res);
        }
        if (right > left) {
            generate(left, right - 1, str + ")", res);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

}
