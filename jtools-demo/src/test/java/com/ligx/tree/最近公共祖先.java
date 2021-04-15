package com.ligx.tree;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 最近公共祖先 {

    public boolean pushIntoStack(Node root, Node node, Stack<Node> stack) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            stack.push(root);
            return true;
        }
        if (pushIntoStack(root.left, node, stack) || pushIntoStack(root.right, node, stack)) {
            stack.push(root);
            return true;
        }
        return false;
    }

    public Node findCommonParentNode(Node root, Node node1, Node node2) {
        Stack<Node> stack1 = new Stack<>();
        pushIntoStack(root, node1, stack1);

        Stack<Node> stack2 = new Stack<>();
        pushIntoStack(root, node2, stack2);

        Node temp = null;
        while (stack1.peek() == stack2.peek()) {
            temp = stack1.pop();
            stack2.pop();
        }
        return temp;
    }
}
