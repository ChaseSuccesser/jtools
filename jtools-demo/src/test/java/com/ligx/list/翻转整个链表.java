package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 翻转整个链表 {

    private static void reverse(Link head) {
        Link curr = head.next;
        Link next;
        Link nextNext;

        if (curr == null || curr.next == null) {
            return;
        }

        while (curr.next != null) {
            next = curr.next;
            nextNext = next.next;
            next.next = head.next;
            head.next = next;
            curr.next = nextNext;
        }
    }


    public static void main(String[] args) {
        Link link1 = new Link(null, 5);
        Link link2 = new Link(link1, 4);
        Link link3 = new Link(link2, 3);
        Link link4 = new Link(link3, 2);
        Link link5 = new Link(link4, 1);

        Link head = new Link(link5, 0);

        reverse(head);

        // test
        Link curr = head.next;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}
