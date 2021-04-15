package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 求二叉树叶子节点个数 {

    public int treeLeafCount(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftLeafCount = treeLeafCount(root.left);
        int rightLeafCount = treeLeafCount(root.right);
        return leftLeafCount + rightLeafCount;
    }
}
