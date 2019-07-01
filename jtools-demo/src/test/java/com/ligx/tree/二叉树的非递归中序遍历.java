package com.ligx.tree;

import java.util.Stack;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 二叉树的非递归中序遍历 {

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            if (!stack.isEmpty()) {
                Node temp = stack.pop();
                System.out.println(temp.getData());
                curr = temp.getRight();
            }
        }
    }
}
