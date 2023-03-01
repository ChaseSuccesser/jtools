package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2023年03月02日.
 */
public class KGroup反转 {

    private Link reverse(Link head, int k) {
        Link dummy = new Link();
        dummy.next = head;
        Link pre = dummy;

        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                doReverse(pre, head.next);
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }

    private void doReverse(Link pre, Link next) {
        Link last = pre.next;
        Link curr = last.next;
        while (curr != next) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }
    }
}
