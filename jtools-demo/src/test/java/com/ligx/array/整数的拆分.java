package com.ligx.array;

import java.util.Vector;

/**
 * @author: ligongxing.
 * @date: 2023年03月09日.
 */
public class 整数的拆分 {

    private static int count = 0;

    private static void dfs(int sum, Vector<Integer> vector, int currNum, int id) {
        if (currNum == sum) {
            System.out.print("方案" + ++count + ":");
            for (int i = 0; i < vector.size(); i++) {
                System.out.print(vector.get(i) + " ");
            }
            System.out.println();
            return;
        }
        for (int i = id; i <= sum; i++) {
            if (currNum + i <= sum) {
                vector.add(i);
                dfs(sum, vector, currNum + i, i);
                vector.remove(vector.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        dfs(6, vector, 0, 1);
    }
}
