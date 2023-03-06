package com.ligx.string;

import java.util.Arrays;

/**
 * @author: ligongxing.
 * @date: 2023年03月06日.
 */
public class 回文分割2 {

    public int minCut(String s) {
        boolean[][] table = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            table[i][i] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            int l = i - 1;
            int r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                table[l--][r++] = true;
            }
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                table[l--][r++] = true;
            }
        }

        int[] f = new int[s.length()];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < s.length(); i++) {
            if (table[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (table[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[s.length() - 1];
    }

}
