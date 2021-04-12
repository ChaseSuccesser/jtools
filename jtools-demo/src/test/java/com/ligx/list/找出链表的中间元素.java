package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 找出链表的中间元素 {

    public Link findMiddle(Link head) {
        if (head == null) {
            return null;
        }
        Link fast = head;
        Link slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
