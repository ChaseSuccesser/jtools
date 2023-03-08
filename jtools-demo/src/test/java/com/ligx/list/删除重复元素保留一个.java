package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 删除重复元素保留一个 {

    private static Link deleteDuplicate(Link head) {
        if (head == null || head.next == null) {
            return head;
        }
        Link pre = head;
        Link curr = head.next;
        while (pre != null && curr != null) {
            if (pre.data == curr.data) {
                pre.next = curr.next;
            } else {
                pre = pre.next;
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link5 = new Link(link6, 5);
        Link link4 = new Link(link5, 4);
        Link link3 = new Link(link4, 4);
        Link link2 = new Link(link3, 2);
        Link link1 = new Link(link2, 1);

        deleteDuplicate(link1);

        Link curr = link1;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
