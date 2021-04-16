package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月15日.
 */
public class Heap {

    private int maxSize;
    private int currentSize;
    private int[] heapArray;

    public Heap(int maxSize) {
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

    public void trickleUp(int index) {
        int bottom = heapArray[index];
        int parent = (index - 1) / 2;
        while (index > 0 && heapArray[parent] < bottom) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (index - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public int remove() {
        int root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int top = heapArray[index];
        int largeChild;
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize && heapArray[leftChild] < heapArray[rightChild]) {
                largeChild = rightChild;
            } else {
                largeChild = leftChild;
            }
            if (top >= heapArray[largeChild]) {
                break;
            }
            heapArray[index] = heapArray[largeChild];
            index = largeChild;
        }
        heapArray[index] = top;
    }

}
