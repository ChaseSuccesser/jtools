package com.ligx.array;

import java.util.Vector;

/**
 * @author: ligongxing.
 * @date: 2023年03月09日.
 */
public class 找零钱 {

    private static int count = 0;

    private static void dfs(int sum, int[] money, Vector<Integer> vector, int currNum, int id) {
        if (currNum == sum) {
            System.out.print("方案" + ++count + ":");
            for (int i = 0; i < vector.size(); i++) {
                System.out.print(vector.get(i) + " ");
            }
            System.out.println();
            return;
        }
        for (int i = id; i < money.length; i++) {
            if (currNum + money[i] <= sum) {
                vector.add(money[i]);
                dfs(sum, money, vector, currNum + money[i], i);
                vector.remove(vector.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        int[] money = { 1, 2, 5, 10, 20 };
        dfs(20, money, vector, 0, 0);
    }
}
