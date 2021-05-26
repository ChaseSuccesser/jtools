package com.ligx.array;

import java.util.Arrays;

/**
 * @author: ligongxing.
 * @date: 2021年05月24日.
 */
public class 给定区间第k小的元素 {

    public static int findKBetweenBeginAndEnd(int[] arr, int start, int end, int k) {
        Node[] nodeArr = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodeArr[i] = new Node(arr[i], i);
        }
        Arrays.sort(nodeArr);

        for (int i = 0; i < nodeArr.length; i++) {
            if (start <= nodeArr[i].index && nodeArr[i].index <= end) {
                k--;
            }
            if (k == 0) {
                return nodeArr[i].item;
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
            return this.getItem() - o.getItem();
        }

        public int getItem() {
            return item;
        }

        public void setItem(int item) {
            this.item = item;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}