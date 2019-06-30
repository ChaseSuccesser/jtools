package com.ligx.string;

import java.util.BitSet;

/**
 * Author: ligongxing.
 * Date: 2019年06月30日.
 */
public class 字符串是否包含问题 {

    public static boolean checkContain(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        BitSet bitSet = new BitSet();
        for (int i = 0; i < chars1.length; i++) {
            bitSet.set((int)chars1[i]);
        }
        for (int i = 0; i < chars2.length; i++) {
            if (!bitSet.get((int) chars2[i])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String str1 = "aABCDEFGHLMNOPQRSO";
        String str2 = "DCGSRaQPO";
        System.out.println(checkContain(str1, str2));
    }
}

