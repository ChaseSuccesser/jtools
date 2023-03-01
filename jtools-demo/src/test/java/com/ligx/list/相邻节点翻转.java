package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 相邻节点翻转 {

    private static void reverse(Link head) {
        if (head == null) {
            return;
        }
        Link trail = head;
        Link next;
        Link nextNext;

        while (trail.next != null && trail.next.next != null) {
            next = trail.next;
            nextNext = trail.next.next;

            trail.next = nextNext;
            next.next = nextNext.next;
            nextNext.next = next;

            trail = next;
        }
    }

    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link1 = new Link(link6, 5);
        Link link2 = new Link(link1, 4);
        Link link3 = new Link(link2, 3);
        Link link4 = new Link(link3, 2);
        Link link5 = new Link(link4, 1);

        Link head = new Link(link5, 0);

        reverse(head);

        // test
        Link curr = head.next;
        while (curr.next != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
        System.out.println(curr.data);
    }
}
