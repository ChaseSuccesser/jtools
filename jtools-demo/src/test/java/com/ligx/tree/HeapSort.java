package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class HeapSort {

    /**
     * 堆排序
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Heap heap = new Heap(arr.length);

        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.remove();
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 5};
        heapSort(arr);
    }
}
