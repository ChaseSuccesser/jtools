package com.ligx.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 回文分割 {

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        dfs(res, tmp, s, table(s), 0);
        return res;
    }

    public static void dfs(List<List<String>> res, List<String> temp, String s, boolean[][] table, int pos) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = pos; i < s.length(); i++) {
            if (table[pos][i]) {
                temp.add(s.substring(pos, i + 1));
                dfs(res, temp, s, table, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static boolean[][] table(String str) {
        boolean[][] table = new boolean[str.length()][str.length()];

        for (int i = 0; i < str.length(); i++) {
            table[i][i] = true;
        }

        for (int i = 0; i < str.length(); i++) {
            int l = i - 1;
            int r = i;
            while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
                table[l--][r--] = true;
            }
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
                table[l--][r--] = true;
            }
        }

        return table;
    }
}
