package com.ligx.list;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 从尾到头打印链表 {

    public void rPrintLinkByStack(Link head) {
        if (head == null) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        Link node = head;
        while (node != null) {
            stack.push(node.data);
            node = node.next;
        }

        while (!stack.isEmpty()) {
            Integer data = stack.peek();
            System.out.println(data);
            stack.pop();
        }
    }


    public void rPrintLinkByRecursion(Link head) {
        if (head == null) {
            return;
        }
        rPrintLinkByRecursion(head.next);
        System.out.println(head.data);
    }
}
