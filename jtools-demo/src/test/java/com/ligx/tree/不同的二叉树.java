package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2023年03月11日.
 */
public class 不同的二叉树 {

    public static long numTrees(int n) {
        long c = 1;
        for (int i = 0; i < n; i++) {
            c = 2 * c * (2 * i + 1) / (i + 2);
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(numTrees(7));
    }
}
