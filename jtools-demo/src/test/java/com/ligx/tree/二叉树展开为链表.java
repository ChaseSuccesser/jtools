package com.ligx.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ligongxing.
 * @date: 2023年03月10日.
 */
public class 二叉树展开为链表 {

    private static void expandTree(Node root) {
        List<Node> list = new ArrayList<>();
        preOrder(root, list);

        for (int i = 1; i < list.size(); i++) {
            Node pre = list.get(i - 1);
            pre.right = list.get(i);
            pre.left = null;
        }
    }

    private static void preOrder(Node root, List<Node> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }
}
