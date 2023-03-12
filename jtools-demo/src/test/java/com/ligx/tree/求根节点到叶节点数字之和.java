package com.ligx.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2023年03月12日.
 */
public class 求根节点到叶节点数字之和 {

    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        sumNumbers(root, stack, list);

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    private void sumNumbers(TreeNode root, Stack<Integer> stack, List<Integer> list) {
        if (root == null) {
            return;
        }

        stack.push(root.val);

        if (root.left == null && root.right == null) {
            Stack<Integer> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }
            int num = 0;
            while (!tempStack.isEmpty()) {
                num = num * 10 + tempStack.peek();
                stack.push(tempStack.pop());
            }
            list.add(num);
        }

        sumNumbers(root.left, stack, list);
        sumNumbers(root.right, stack, list);

        stack.pop();
    }
}
