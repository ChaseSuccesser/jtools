package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 翻转整个链表 {

    public void reverse(Link head) {
        Link curr = head.next;
        Link next = null;
        Link nextnext = null;

        if (curr == null || curr.next == null) {
            return;
        }

        while (curr.next != null) {
            next = curr.next;
            nextnext = next.next;
            next.next = head.next;
            head.next = next;
            curr.next = nextnext;
        }
    }
}
