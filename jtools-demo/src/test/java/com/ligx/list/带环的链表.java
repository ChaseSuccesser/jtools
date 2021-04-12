package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 带环的链表 {

    public boolean isCircle(Link head) {
        if (head == null) {
            return false;
        }
        Link first = head;
        Link second = head;
        if (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
            if (second == first) {
                return true;
            }
        }
        return false;
    }

    public int circleLength(Link point) {
        int length = 1;
        Link curr = point;
        while (curr.next != point) {
            length++;
            curr = curr.next;
        }
        return length;
    }
}
