package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 合并两个有序的链表 {

    private Link mergeTwoLink2(Link head1, Link head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        Link head = new Link();
        Link tail = head;

        Link a = head1;
        Link b = head2;
        while (a != null && b != null) {
            if (a.data < b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
        return head.next;
    }

    public Link mergeTwoLink(Link head1, Link head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Link head = new Link();
        Link pre = head;
        Link curr;

        Link curr1 = head1;
        Link curr2 = head2;

        while (curr1.next != null && curr2.next != null) {
            if (curr1.next.data < curr2.next.data) {
                curr = new Link(null, curr1.next.data);
                curr1 = curr1.next;
            } else if (curr1.next.data > curr2.next.data) {
                curr = new Link(null, curr2.next.data);
                curr2 = curr2.next;
            } else {
                curr = new Link(null, curr1.next.data);
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            pre.next = curr;
            pre = pre.next;
        }

        while (curr1.next != null) {
            curr = new Link(null, curr1.next.data);
            curr1 = curr1.next;
            pre.next = curr;
            pre = pre.next;
        }

        while (curr2.next != null) {
            curr = new Link(null, curr2.next.data);
            curr2 = curr2.next;
            pre.next = curr;
            pre = pre.next;
        }

        return head;
    }
}
