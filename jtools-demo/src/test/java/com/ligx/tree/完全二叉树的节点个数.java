package com.ligx.tree;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月12日.
 */
public class 完全二叉树的节点个数 {

    public int countNodes(TreeNode root) {
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        int i = 0;
        while (!queue.isEmpty()) {
            queue.poll();
            i++;
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }
        return i;
    }
}
