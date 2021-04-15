package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 求二叉树的深度 {

    public int treeDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
