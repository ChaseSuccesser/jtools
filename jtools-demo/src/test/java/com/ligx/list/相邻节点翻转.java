package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 相邻节点翻转 {

    public void reverse(Node head) {
        if (head == null) {
            return;
        }
        Node p;
        Node temp;
        Node trail = head;
        while (trail.next != null && trail.next.next != null) {
            p = trail.next;
            temp = p.next;

            trail.next = temp;
            p.next = temp.next;
            temp.next = p;

            trail = p;
        }
    }
}
