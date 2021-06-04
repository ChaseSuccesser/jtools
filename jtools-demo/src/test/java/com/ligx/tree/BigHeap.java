package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class BigHeap {

    private int maxSize;
    private int currentSize;
    private int[] heapArray;

    public BigHeap(int maxSize) {
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
        // 后面这个判断是与最小堆不同的地方
        while (index > 0 && heapArray[parent] < temp) {
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
            int largeChild;
            // 后面这个判断是与最小堆不同的地方
            if (rightChild < currentSize && heapArray[rightChild] > heapArray[leftChild]) {
                largeChild = rightChild;
            } else {
                largeChild = leftChild;
            }
            // 这是与最小堆不同的地方
            if (temp >= heapArray[largeChild]) {
                break;
            }
            heapArray[index] = heapArray[largeChild];
            index = largeChild;
        }
        heapArray[index] = temp;
    }

    public int peekRoot() {
        return heapArray[0];
    }
}
