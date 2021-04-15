package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 二叉树的镜像 {

    public void preOrder(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        preOrder(root.left);
        preOrder(root.right);
    }
}
