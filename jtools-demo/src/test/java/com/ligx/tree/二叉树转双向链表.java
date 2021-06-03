package com.ligx.tree;

import com.ligx.list.Link;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 二叉树转双向链表 {

    private static void convertTreeToLink(Node root, Link link) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            convertTreeToLink(root.right, link);
        }

        link.pre = new Link(null, link, root.data);
        link = link.pre;

        if (root.left != null) {
            convertTreeToLink(root.left, link);
        }
    }
}
