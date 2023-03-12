package com.ligx.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2021年06月03日.
 */
public class 和为某一值的所有路径 {


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();

        pathSum(root, targetSum, stack, res);
        return res;
    }

    private void pathSum(TreeNode root, int targetSum, Stack<Integer> stack, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        stack.push(root.val);
        targetSum -= root.val;

        if (root.left == null && root.right == null && targetSum == 0) {
            Stack<Integer> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }
            List<Integer> list = new ArrayList<>();
            while (!tempStack.isEmpty()) {
                list.add(tempStack.peek());
                stack.push(tempStack.pop());
            }
            res.add(list);
        }

        pathSum(root.left, targetSum, stack, res);
        pathSum(root.right, targetSum, stack, res);

        stack.pop();
    }
}
