package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2023年03月04日.
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
