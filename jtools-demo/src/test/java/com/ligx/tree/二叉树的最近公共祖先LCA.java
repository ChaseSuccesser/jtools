package com.ligx.tree;

import java.util.Stack;

/**
 * Author: ligongxing.
 * Date: 2021/06/03.
 */
public class 二叉树的最近公共祖先LCA {

    private static boolean getPositionByNode(Node root, Node node, Stack<Node> stack) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            stack.push(root);
            return true;
        }
        if (getPositionByNode(root.left, node, stack) || getPositionByNode(root.right, node, stack)) {
            stack.push(root);
            return true;
        }
        return false;
    }

    private static Node findCommonParentNode(Node root, Node node1, Node node2) {
        Stack<Node> stack1 = new Stack<>();
        getPositionByNode(root, node1, stack1);

        Stack<Node> stack2 = new Stack<>();
        getPositionByNode(root, node2, stack2);

        Node temp = null;
        while (stack1.peek() == stack2.peek()) {
            temp = stack1.pop();
            stack2.pop();
        }
        return temp;
    }
}
