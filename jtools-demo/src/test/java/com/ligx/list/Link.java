package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class Link {

    public Link pre;
    public Link next;
    public int data;

    public Link() {
    }

    public Link(Link next, int data) {
        this.next = next;
        this.data = data;
    }

    public Link(Link pre, Link next, int data) {
        this.pre = pre;
        this.next = next;
        this.data = data;
    }
}
