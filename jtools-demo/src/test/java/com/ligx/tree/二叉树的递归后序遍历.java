package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月13日.
 */
public class 二叉树的递归后序遍历 {

    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }
}
