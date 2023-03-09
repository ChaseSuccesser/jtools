package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 合并两个有序的链表 {

    private static Link mergeTwoLink(Link head1, Link head2) {
        Link dummy = new Link();
        Link trail = dummy;

        Link curr1 = head1;
        Link curr2 = head2;
        while (curr1 != null && curr2 != null) {
            if (curr1.data < curr2.data) {
                trail.next = curr1;
                curr1 = curr1.next;
            } else {
                trail.next = curr2;
                curr2 = curr2.next;
            }
            trail = trail.next;
        }
        trail.next = curr1 != null ? curr1 :curr2;

        return dummy.next;
    }

    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link5 = new Link(link6, 4);
        Link link4 = new Link(link5, 2);

        Link link3 = new Link(null, 5);
        Link link2 = new Link(link3, 3);
        Link link1 = new Link(link2, 1);

        Link head = mergeTwoLink(link1, link4);

        Link curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
