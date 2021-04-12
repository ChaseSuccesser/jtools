package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 以O1时间复杂度删除节点 {

    public void deleteNode(Node head, Node toBeDeleted) {
        if (head == null || toBeDeleted == null) {
            return;
        }
        if (toBeDeleted.next != null) {
            Node temp = toBeDeleted.next;
            toBeDeleted.data = temp.data;
            toBeDeleted.next = temp.next;
            temp = null;
        } else {
            Node node = head;
            while (node.next != toBeDeleted) {
                node = node.next;
            }
            node.next = null;
            toBeDeleted = null;
        }
    }
}
