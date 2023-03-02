package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 二叉树转双向链表 {

    private Node pre = null;
    private void convertChild(Node curr) {
        if (curr == null) {
            return;
        }
        convertChild(curr.left);

        curr.left = pre;
        if (pre != null) {
            pre.right = curr;
        }
        pre = curr;

        convertChild(curr.right);
    }

    private Node convert(Node root) {
        if (root == null) {
            return null;
        }

        convertChild(root);

        Node head = root;
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }
}
