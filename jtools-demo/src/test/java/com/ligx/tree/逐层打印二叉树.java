package com.ligx.tree;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 逐层打印二叉树 {

    public void print(Node root) {
        if (root == null) {
            return;
        }
        ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<>(100);
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.println(temp.data);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }
}
