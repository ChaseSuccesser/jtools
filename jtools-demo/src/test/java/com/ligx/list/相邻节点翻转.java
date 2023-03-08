package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 相邻节点翻转 {

    private static Link reverse(Link head) {
        // 新建一个临时节点，挂在头结点的前面
        Link dummy = new Link();
        dummy.next = head;

        Link trail = dummy;
        Link next;
        Link nextN;
        // 因为下面的代码要把trail后面的两个节点反转，所以后面这俩节点不能为空，如果有一个为，则不需要反转了
        while (trail.next != null && trail.next.next != null) {
            next = trail.next;
            nextN = trail.next.next;
            trail.next = nextN;
            next.next = nextN.next;
            nextN.next = next;
            trail = next;
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
