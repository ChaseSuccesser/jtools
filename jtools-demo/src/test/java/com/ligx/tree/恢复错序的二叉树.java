package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2023年03月11日.
 */
public class 恢复错序的二叉树 {

    private TreeNode firstMax = null;
    private TreeNode LastMin = null;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);

    public void reconvertTree(TreeNode root) {
        inOrder(root);

        if (firstMax != null && LastMin != null) {
            int temp = firstMax.val;
            firstMax.val = LastMin.val;
            LastMin.val = temp;
        }
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);

        if (pre.val > root.val) {  //找到不满足升序顺序的位置
            if (firstMax == null) {
                // 记录第一个升序错乱的最大值节点。因为要求是第一个，所以值更新一次就行，后面即使还有升序错乱的位置，也不更新firstMax了
                firstMax = pre;
            }
            // 记录最后一个升序错乱的最小值节点
            LastMin = root;
        }

        pre = root; // pre指针后移

        inOrder(root.right);
    }
}
