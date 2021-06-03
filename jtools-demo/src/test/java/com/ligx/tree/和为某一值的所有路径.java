package com.ligx.tree;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2021年06月03日.
 */
public class 和为某一值的所有路径 {

    private static void findBinNode(Node root, int sum, Stack<Node> stack) {
        if (root == null) {
            return;
        }
        if (root.data != sum) {
            stack.push(root);
        } else {

        }
        if (root.left != null) {
            findBinNode(root.left, sum - root.data, stack);
        }
        if (root.right != null) {
            findBinNode(root.right, sum - root.data, stack);
        }
        stack.pop();
    }
}
