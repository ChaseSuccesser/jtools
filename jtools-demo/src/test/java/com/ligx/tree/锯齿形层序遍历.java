package com.ligx.tree;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月11日.
 */
public class 锯齿形层序遍历 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(root);
        int j = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (j % 2 != 0) {
                reverse(list, 0, list.size() - 1);
            }
            res.add(list);

            j++;
        }
        return res;
    }

    private void reverse(List<Integer> list, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    @Test
    public void test() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        reverse(list, 0, list.size() - 1);
        System.out.println(list);
    }
}
