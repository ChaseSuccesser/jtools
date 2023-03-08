package com.ligx.list;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 从尾到头打印链表 {

    private static void reversePrint(Link head) {
        Stack<Link> stack = new Stack<>();
        Link curr = head;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        while (!stack.isEmpty()) {
            Link temp = stack.pop();
            System.out.print(temp.data + " ");
        }
    }

    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link5 = new Link(link6, 5);
        Link link4 = new Link(link5, 4);
        Link link3 = new Link(link4, 3);
        Link link2 = new Link(link3, 2);
        Link link1 = new Link(link2, 1);

        reversePrint(link1);
    }
}
