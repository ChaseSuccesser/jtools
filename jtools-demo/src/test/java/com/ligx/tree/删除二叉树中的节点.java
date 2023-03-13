package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2023年03月12日.
 */
public class 删除二叉树中的节点 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        else if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.right = deleteNode(root.right, successor.val);
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }
    }
}
