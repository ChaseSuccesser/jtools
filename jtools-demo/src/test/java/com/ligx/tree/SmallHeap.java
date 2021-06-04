package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class SmallHeap {

    private int maxSize;
    private int currentSize;
    private int[] heapArray;

    public SmallHeap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heapArray = new int[maxSize];
    }

    public void insert(int key) {
        if (currentSize == maxSize) {
            return;
        }
        heapArray[currentSize] = key;
        trickleUp(currentSize++);
    }

    private void trickleUp(int index) {
        int temp = heapArray[index];
        int parent = (index - 1) / 2;
        while (index > 0 && heapArray[parent] > temp) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (index - 1) / 2;
        }
        heapArray[index] = temp;
    }

    public int remove() {
        if (currentSize == 0) {
            throw new IllegalStateException("堆是空的");
        }
        int root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    private void trickleDown(int index) {
        int temp = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            int smallChild;
            if (rightChild < currentSize && heapArray[rightChild] < heapArray[leftChild]) {
                smallChild = rightChild;
            } else {
                smallChild = leftChild;
            }
            if (temp <= heapArray[smallChild]) {
                break;
            }
            heapArray[index] = heapArray[smallChild];
            index = smallChild;
        }
        heapArray[index] = temp;
    }

    public int peekRoot() {
        return heapArray[0];
    }
}
