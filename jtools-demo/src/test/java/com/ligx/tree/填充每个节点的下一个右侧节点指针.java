package com.ligx.tree;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月12日.
 */
public class 填充每个节点的下一个右侧节点指针 {

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                Node node = queue.poll();

                if (i < n - 1) {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}
