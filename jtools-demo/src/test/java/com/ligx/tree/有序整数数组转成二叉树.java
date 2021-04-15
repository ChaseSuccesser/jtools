package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class 有序整数数组转成二叉树 {

    public void insertArrayIntoTree(int[] arr, int pos, Node node) {
        node = new Node(arr[pos], null, null);

        if (pos * 2 + 1 > arr.length - 1) {
            return;
        } else {
            insertArrayIntoTree(arr, pos * 2 + 1, node.left);
        }

        if (pos * 2 + 2 > arr.length - 1) {
            return;
        } else {
            insertArrayIntoTree(arr, pos * 2 + 2, node.right);
        }
    }
}
