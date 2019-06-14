package com.ligx;

import org.junit.Test;
import sun.text.normalizer.Trie;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 扑克牌顺序 {

    public boolean shunzi(int[] a) {
        Arrays.sort(a);

        int numbOfZero = 0;
        int numOfGap = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                numbOfZero++;
            }
        }

        for (int i = 1; i < a.length; i++) {
            numOfGap += a[i] - a[i - 1] - 1;
        }

        return (numOfGap > numbOfZero) ? false : true;
    }


    @Test
    public void test() {
        System.out.println(shunzi(new int[]{1, 2, 3, 4}));
        System.out.println(shunzi(new int[]{1, 2, 0, 4}));
        System.out.println(shunzi(new int[]{1, 0, 0, 4}));
        System.out.println(shunzi(new int[]{1, 0, 0, 5}));
    }
}
