package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2023年03月01日.
 */
public class 判断两个链表是否相交 {

    private static boolean judgeIntersectLink(Link head1, Link head2) {
        Link curr1 = head1;
        Link curr2 = head2;

        while (curr1.next != null) {
            curr1 = curr1.next;
        }

        while (curr2.next != null) {
            curr2 = curr2.next;
        }

        return curr1 == curr2;
    }
}
