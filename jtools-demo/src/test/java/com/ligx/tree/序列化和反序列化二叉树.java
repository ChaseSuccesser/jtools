package com.ligx.tree;

import java.util.*;

/**
 * @author: ligongxing.
 * @date: 2023年03月13日.
 */
public class 序列化和反序列化二叉树 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        String str = list.toString();
        return str.substring(1, str.length() - 1);
    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split(", ");

        int[] preOrder = new int[arr.length];
        int[] inOrder = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            preOrder[i] = Integer.parseInt(arr[i]);
            inOrder[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(inOrder);

        return buildTree(preOrder, inOrder);
    }


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
