package com.ligx.tree;

import lombok.Data;

/**
 * Author: ligongxing.
 * Date: 2019/07/01.
 */
@Data
public class Node {

    public int data;

    public Node left;

    public Node right;

    public Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
