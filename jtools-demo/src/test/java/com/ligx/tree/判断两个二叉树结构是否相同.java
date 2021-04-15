package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 判断两个二叉树结构是否相同 {

    public boolean isSame(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        }
        boolean left = isSame(root1.left, root2.left);
        boolean right = isSame(root1.right, root2.right);
        return left && right;
    }
}
