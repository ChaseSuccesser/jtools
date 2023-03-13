package com.ligx.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ligongxing.
 * @date: 2023年03月13日.
 */
public class 从中序与后序遍历序列构造二叉树 {

    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        int n = inOrder.length;
        for (int i = 0; i < n; i++) {
            map.put(inOrder[i], i);
        }
        return buildTree(inOrder, postOrder, 0, n - 1, 0, n - 1);
    }

    private TreeNode buildTree(int[] inOrder, int[] postOrder, int inOrderLeft, int inOrderRight, int postOrderLeft, int postOrderRight) {
        if (postOrderLeft > postOrderRight) {
            return null;
        }

        int postOrderRoot = postOrder[postOrderRight];
        TreeNode root = new TreeNode(postOrderRoot);

        int inOrderRootIndex = map.get(postOrderRoot);
        int leftSize = inOrderRootIndex - inOrderLeft;

        root.left = buildTree(inOrder, postOrder, inOrderLeft, inOrderRootIndex - 1, postOrderLeft, postOrderLeft + leftSize - 1);
        root.right = buildTree(inOrder, postOrder, inOrderRootIndex + 1, inOrderRight, postOrderLeft + leftSize, postOrderRight - 1);
        return root;
    }
}
