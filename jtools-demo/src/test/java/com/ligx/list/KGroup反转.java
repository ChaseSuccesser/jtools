package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2023年03月02日.
 */
public class KGroup反转 {

    private static Link reverseKGroup(Link head, int k) {
        Link dummy = new Link();
        dummy.next = head;
        Link pre = dummy;

        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                Link next = head.next;
                pre = doReverse(pre, next);
                head = next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }

    private static Link doReverse(Link pre, Link next) {
        Link last = pre.next;
        Link curr = last.next;
        while (curr != next) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }
        return last;
    }

    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link5 = new Link(link6, 5);
        Link link4 = new Link(link5, 4);
        Link link3 = new Link(link4, 3);
        Link link2 = new Link(link3, 2);
        Link link1 = new Link(link2, 1);

        Link head = reverseKGroup(link1, 3);

        Link curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
