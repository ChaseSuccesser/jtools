package com.ligx.array;

import org.junit.Test;

import java.util.Vector;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 整数拆分和找零钱问题 {

    private static int count = 0;

    // 整数拆分
    public static void dfs1(int sum, Vector<Integer> vector, int currnum, int id) {
        if (currnum == sum) {
            System.out.print("方案" + (count++) + ":");
            for (int i = 0; i < vector.size(); i++) {
                System.out.print(vector.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = id; i <= sum; i++) {
            if (currnum + i <= sum) {
                vector.add(i);
                dfs1(sum, vector, currnum + i, i);
                vector.remove(vector.size() - 1);
            }
        }
    }

    // 整数拆分的测试
    @Test
    public void dfs1Test() {
        Vector<Integer> vector = new Vector<>();

        dfs1(6, vector, 0, 1);
    }



    // 找零钱
    public static void dfs2(int sum, Vector<Integer> vector, int[] money, int currnum, int id) {
        if (currnum == sum) {
            System.out.print("方案" + (count++) + ":");
            for (int i = 0; i < vector.size(); i++) {
                System.out.print(vector.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = id; i < money.length; i++) {
            if (currnum + money[i] <= sum) {
                vector.add(money[i]);
                dfs2(sum, vector, money, currnum + money[i], i);
                vector.remove(vector.size() - 1);
            }
        }
    }

    // 找零钱的测试
    @Test
    public void dfs2Test() {
        Vector<Integer> vec = new Vector<>();
        int[] money = {1, 2, 5, 10, 20};
        dfs2(20, vec, money, 0, 0);
    }
}
