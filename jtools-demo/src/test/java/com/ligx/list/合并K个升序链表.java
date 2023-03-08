package com.ligx.list;

import java.util.PriorityQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月04日.
 */
public class 合并K个升序链表 {


    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link5 = new Link(link6, 4);
        Link link4 = new Link(link5, 2);

        Link link3 = new Link(null, 5);
        Link link2 = new Link(link3, 3);
        Link link1 = new Link(link2, 1);

        Link head = mergeList(new Link[]{link1, link4});

        Link curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    private static Link mergeList(Link[] lists) {
        PriorityQueue<Status> queue = new PriorityQueue<>();

        for (Link head : lists) {
            if (head != null) {
                queue.offer(new Status(head.data, head));
            }
        }

        Link dummy = new Link();
        Link trail = dummy;
        while (!queue.isEmpty()) {
            Link temp = queue.poll().node;
            trail.next = temp;
            trail = trail.next;
            if (temp.next != null) {
                queue.offer(new Status(temp.next.data, temp.next));
            }
        }
        return dummy.next;
    }

    static class Status implements Comparable<Status> {
        private int data;
        private Link node;

        public Status() {
        }

        public Status(int data, Link node) {
            this.data = data;
            this.node = node;
        }

        @Override
        public int compareTo(Status o) {
            return this.data - o.data;
        }
    }
}
