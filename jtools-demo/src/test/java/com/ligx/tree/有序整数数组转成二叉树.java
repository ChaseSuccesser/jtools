package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 有序整数数组转成二叉树 {

    public void insertArrayIntoTree(int[] arr) {
        dfs(arr, 0, arr.length - 1);
    }

    private Node dfs(int[] arr, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        Node root = new Node(arr[mid]);

        root.left = dfs(arr, left, mid - 1);
        root.right = dfs(arr, mid + 1, right);

        return root;
    }
}
