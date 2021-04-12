package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 找出倒数第4个元素 {

    public Link getLast4thOne(Link head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        Link first = head;
        Link second = head;
        for (int i = 0; i < k; i++) {
            if (first.next == null) {
                return null;
            }
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }
}
