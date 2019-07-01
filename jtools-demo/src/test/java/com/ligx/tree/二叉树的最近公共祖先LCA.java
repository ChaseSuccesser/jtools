package com.ligx.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
public class 二叉树的最近公共祖先LCA {

    public static void lca(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }
}
