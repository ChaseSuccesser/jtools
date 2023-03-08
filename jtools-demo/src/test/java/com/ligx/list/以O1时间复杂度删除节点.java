package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 以O1时间复杂度删除节点 {

    private static void deleteNode(Link head, Link toBeDeletedNode) {
        if (head == null || toBeDeletedNode == null) {
            return;
        }
        if (toBeDeletedNode.next != null) {
            Link temp = toBeDeletedNode.next;
            toBeDeletedNode.next = temp.next;
            toBeDeletedNode.data = temp.data;
        } else {
            Link pre = head;
            while (pre.next != toBeDeletedNode) {
                pre = pre.next;
            }
            pre.next = null;
        }
    }

    public static void main(String[] args) {
        Link link6 = new Link(null, 6);
        Link link5 = new Link(link6, 5);
        Link link4 = new Link(link5, 4);
        Link link3 = new Link(link4, 3);
        Link link2 = new Link(link3, 2);
        Link link1 = new Link(link2, 1);

        deleteNode(link1, link6);

        Link curr = link1;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
