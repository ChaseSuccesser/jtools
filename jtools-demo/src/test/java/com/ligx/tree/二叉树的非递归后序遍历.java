package com.ligx.tree;

import java.util.Stack;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 二叉树的非递归后序遍历 {

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Node visited = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            curr = stack.peek();
            if (curr.getRight() == null || curr.getRight() == visited) {
                stack.pop();
                System.out.println(curr.getData());
                visited = curr;
                curr = null;
            } else {
                curr = curr.getRight();
            }
        }
    }
}
