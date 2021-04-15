package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月13日.
 */
public class 二叉树的递归前序遍历 {

    public void preOrder(Node root) {
        if (root != null) {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
}
