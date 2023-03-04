package com.ligx.list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月04日.
 */
public class 合并K个升序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Status> queue = new PriorityQueue<>();

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }

        ListNode head = new ListNode();
        ListNode tail = head;

        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.node;
            tail = tail.next;
            if (f.node.next != null) {
                queue.offer(new Status(f.node.next.val, f.node.next));
            }
        }
        return head.next;
    }

    class Status implements Comparable<Status> {
        private int val;
        private ListNode node;

        public Status(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        public int getVal() {
            return val;
        }

        public ListNode getNode() {
            return node;
        }

        @Override
        public int compareTo(Status o) {
            return this.getVal() - o.getVal();
        }
    }

}
