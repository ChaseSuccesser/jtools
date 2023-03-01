package com.ligx.list;

import org.junit.Assert;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 找出倒数第4个元素 {

    // 1 2 3 4 5 6
    private static Link getLast4thOne(Link head, int k) {
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


    public static void main(String[] args) {
        Link link7 = new Link(null, 7);
        Link link6 = new Link(link7, 6);
        Link link1 = new Link(link6, 5);
        Link link2 = new Link(link1, 4);
        Link link3 = new Link(link2, 3);
        Link link4 = new Link(link3, 2);
        Link link5 = new Link(link4, 1);


        Link link = getLast4thOne(link5, 4);
        Assert.assertTrue(link.data == 4);
        System.out.println(link.data);
    }
}
