package com.ligx.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2023年03月12日.
 */
public class 路径总和 {

    private List<List<Integer>> res = new ArrayList<>();

    public int pathSum(TreeNode root, int targetSum) {
        Stack<TreeNode> stack = new Stack<>();

        dfs(root, targetSum, stack, res);

        if (root != null) {
            pathSum(root.left, targetSum);
            pathSum(root.right, targetSum);
        }

        return res.size();
    }

    private void dfs(TreeNode root, int targetSum, Stack<TreeNode> stack, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        stack.push(root);
        targetSum -= root.val;

        if (targetSum == 0) {
            Stack<TreeNode> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }
            List<Integer> list = new ArrayList<>();
            while (!tempStack.isEmpty()) {
                TreeNode node = tempStack.peek();
                list.add(node.val);
                stack.push(tempStack.pop());
            }
            res.add(list);
        }

        dfs(root.left, targetSum, stack, res);
        dfs(root.right, targetSum, stack, res);

        stack.pop();
    }
}
