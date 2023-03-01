package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2023年03月01日.
 */
public class 找到两个相交链表的相交点 {

    private static Link getIntersect(Link head1, Link head2) {
        Link curr1 = head1;
        Link curr2 = head2;
        int len1 = 0;
        int len2 = 0;

        while (curr1.next != null) {
            len1++;
            curr1 = curr1.next;
        }
        while (curr2.next != null) {
            len2++;
            curr2 = curr2.next;
        }

        curr1 = head1;
        curr2 = head2;
        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                curr1 = curr1.next;
            }
        } else if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++) {
                curr2 = curr2.next;
            }
        }

        while (curr1.next != null) {
            if (curr1 == curr2) {
                return curr1;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return null;
    }
}
