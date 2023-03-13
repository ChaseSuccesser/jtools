package com.ligx.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ligongxing.
 * @date: 2023年03月13日.
 */
public class 从前序与中序遍历序列构造二叉树 {

    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        int n = preOrder.length;
        for (int i = 0; i < n; i++) {
            map.put(inOrder[i], i);
        }
        return buildTree(preOrder, inOrder, 0, n - 1, 0, n - 1);
    }

    private TreeNode buildTree(int[] preOrder, int[] inOrder, int preOrderLeft, int preOrderRight, int inOrderLeft, int inOrderRight) {
        if (preOrderLeft > preOrderRight) {
            return null;
        }

        int preOrderRoot = preOrder[preOrderLeft];
        TreeNode root = new TreeNode(preOrderRoot);

        int inOrderRootIndex = map.get(preOrderRoot);
        int leftSize = inOrderRootIndex - inOrderLeft;

        root.left = buildTree(preOrder, inOrder, preOrderLeft + 1, preOrderLeft + leftSize, inOrderLeft, inOrderRootIndex - 1);
        root.right = buildTree(preOrder, inOrder, preOrderLeft + leftSize + 1, preOrderRight, inOrderRootIndex + 1, inOrderRight);
        return root;
    }
}
