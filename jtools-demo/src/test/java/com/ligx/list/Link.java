package com.ligx.list;

/**
 * @author: ligongxing.
 * @date: 2021年04月12日.
 */
public class Link {

    public Link next;
    public int data;

    public Link() {
    }

    public Link(Link next, int data) {
        this.next = next;
        this.data = data;
    }
}
