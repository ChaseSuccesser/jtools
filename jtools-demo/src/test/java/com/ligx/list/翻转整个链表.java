package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 翻转整个链表 {


    private static Link reverse(Link head) {
        // 如果没有节点或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 新建一个临时节点，挂在头结点的前面
        Link dummy = new Link();
        dummy.next = head;

        Link curr = head;
        Link next;
        Link nextNext;
        while (curr.next != null) {
            next = curr.next;
            nextNext = next.next;
            next.next = dummy.next;
            dummy.next = next;
            curr.next = nextNext;
        }

        //把临时节点后面的真实头节点返回
        return dummy.next;
    }

    public static void main(String[] args) {
        Link link5 = new Link(null, 5);
        Link link4 = new Link(link5, 4);
        Link link3 = new Link(link4, 3);
        Link link2 = new Link(link3, 2);
        Link link1 = new Link(link2, 1);

        Link head = reverse(link1);

        Link curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
