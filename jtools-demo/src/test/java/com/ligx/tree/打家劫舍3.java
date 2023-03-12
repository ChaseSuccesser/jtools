package com.ligx.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ligongxing.
 * @date: 2023年03月12日.
 */
public class 打家劫舍3 {

    private Map<TreeNode, Integer> f = new HashMap<>();
    private Map<TreeNode, Integer> g = new HashMap<>();

    public int rob(TreeNode root) {
        postOrder(root);

        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);

        f.put(root, root.val + g.getOrDefault(root.left, 0) + g.getOrDefault(root.right, 0));
        g.put(root, Math.max(f.getOrDefault(root.left, 0), g.getOrDefault(root.left, 0)) +
                Math.max(f.getOrDefault(root.right, 0), g.getOrDefault(root.right, 0)));
    }
}
