package com.ligx.array;

import org.junit.Test;

import java.util.Vector;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 整数拆分和找零钱问题 {

    private static int count = 1;


    // 整数拆分后，拆分的结果有多少种情况
    public int splitCount(int n) {
        if (n <= 0) {
            return 0;
        }
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == 1 && j == 1) {
                    matrix[i][j] = 1;
                } else if (i > j) {
                    matrix[i][j] = matrix[i][j - 1] + matrix[i - j][j];
                } else if (i == j) {
                    matrix[i][j] = matrix[i][j - 1] + 1;
                } else {
                    matrix[i][j] = matrix[i][i];
                }
            }
        }
        return matrix[n][n];
    }

    @Test
    public void splitCountTest() {
        System.out.println(splitCount(6));
    }

    // ----------------------------------------------------------------------------------------------------

    // 输出整数拆分的所有方案
    private static void dfs1(int sum, Vector<Integer> vector, int currNum, int id) {
        if (currNum == sum) {  //递归的结束条件
            System.out.print(String.format("方案%s:", count++));
            for (int i = 0; i < vector.size(); i++) {
                System.out.print(vector.get(i) +" ");
            }
            System.out.println();
            return;
        }
        for (int i = id; i <= sum; i++) {   //最开始是从id=1开始遍历，结束值就是sum  【重要】
            if (currNum + i <= sum) {
                vector.add(i);                   //将元素添加到向量的末尾
                dfs1(sum, vector, currNum + i, i);   //递归处理
                vector.remove(vector.size() - 1);    //删除最后一个数据
            }
        }
    }

    // 输出整数拆分的所有方案的测试
    @Test
    public void dfs1Test() {
        Vector<Integer> vector = new Vector<>();
        dfs1(6, vector, 0, 1);
    }

    // ----------------------------------------------------------------------------------------------------

    // 找零钱
    public static void dfs2(int sum, Vector<Integer> vector, int[] money, int currNum, int id) {
        if (currNum == sum) {
            System.out.print("方案" + (count++) + ":");
            for (int i = 0; i < vector.size(); i++) {
                System.out.print(vector.get(i) + " ");
            }
            System.out.println();
            return;
        }
        for (int i = id; i < money.length; i++) {
            if (currNum + money[i] <= sum) {
                vector.add(money[i]);
                dfs2(sum, vector, money, currNum + money[i], i);
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
