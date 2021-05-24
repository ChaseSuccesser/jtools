package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 删除重复元素保留一个 {

    public Link deleteDuplicate(Link head) {
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
}
