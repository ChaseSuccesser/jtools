package com.ligx.tree;

import java.util.Stack;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 二叉树的非递归前序遍历 {

    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }
}
