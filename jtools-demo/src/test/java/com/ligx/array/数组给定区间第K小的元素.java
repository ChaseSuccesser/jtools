package com.ligx.array;

import java.util.Arrays;

/**
 * Author: ligongxing.
 * Date: 2019/06/28.
 */
public class 数组给定区间第K小的元素 {

    public static int findKBetweenBeginAndEnd(int[] a, int begin, int end, int k) {
        // 构建结构体
        Node[] nodes = new Node[a.length];
        for (int i = 0; i < a.length; i++) {
            nodes[i] = new Node(a[i], i);
        }

        // 排序
        Arrays.sort(nodes);

        // 检索
        for (int i = 0; i < nodes.length; i++) {
            if (begin <= nodes[i].getIndex() && nodes[i].getIndex() <= end) {
                k--;
            }
            if (k == 0) {
                return nodes[i].getItem();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 6, 2, 7, 3, 8, 1};
        System.out.println(findKBetweenBeginAndEnd(a, 2, 5, 2));
    }

    static class Node implements Comparable<Node> {
        private int item;

        private int index;

        public Node(int item, int index) {
            this.item = item;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.item - o.item;
        }

        public int getItem() {
            return item;
        }

        public int getIndex() {
            return index;
        }
    }
}


