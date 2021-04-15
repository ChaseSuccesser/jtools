package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月13日.
 */
public class 二叉树的递归中序遍历 {

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }
}
