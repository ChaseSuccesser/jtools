package com.ligx.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ligongxing.
 * @date: 2023年03月16日.
 */
public class LRUCache {

    static class DLinkNode {
        private int key;
        private int value;
        private DLinkNode prev;
        private DLinkNode next;

        public DLinkNode() {
        }

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkNode> map = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkNode head = new DLinkNode();
    private DLinkNode tail = new DLinkNode();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode node = map.get(key);
        if (node == null) {
            DLinkNode newNode = new DLinkNode(key, value);
            addToHead(newNode);
            map.put(key, newNode);
            size++;
            if (size > capacity) {
                DLinkNode removedNode = removeTail();
                map.remove(removedNode.key, removedNode);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkNode removeTail() {
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }
}
