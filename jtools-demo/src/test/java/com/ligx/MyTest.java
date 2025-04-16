package com.ligx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ligongxing.
 * @date: 2024年04月21日.
 */
public class MyTest {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node prev;
        public Node next;
        public Node child;
        public Node random;
        public Node left;
        public Node right;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            next = null;
            random = null;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private void reverse(int[] chars, int i, int j) {
        if (i < 0 || j >= chars.length) {
            return;
        }
        while (i < j) {
            swap(chars, i++, j--);
        }
    }

    private void reverse(char[] chars, int i, int j) {
        if (i < 0 || j >= chars.length) {
            return;
        }
        while (i < j) {
            swap(chars, i++, j--);
        }
    }



    @Test
    public void test() throws Exception {
    }
}