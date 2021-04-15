package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 求二叉树的节点数 {

    public int nodeCount(Node root) {
        if(root == null)
            return 0;
        return nodeCount(root.left) + nodeCount(root.right) + 1;
    }
}
