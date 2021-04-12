package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class 删除重复元素一个也不保留 {

    public Link removeDuplicate(Link head) {
        Link begin = new Link();
        begin.next = head;
        Link temp = begin;
        while (temp.next != null && temp.next.next != null) {
            Link curr = temp.next.next;

            boolean isDel = false;
            while (temp.next.data == curr.data) {
                isDel = true;
                curr = curr.next;
            }

            if (isDel) {
                temp.next = curr;
            } else {
                temp = temp.next;
            }
        }

        return begin.next;
    }
}
