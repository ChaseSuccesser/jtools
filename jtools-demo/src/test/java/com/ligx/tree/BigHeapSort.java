package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class BigHeapSort {

    /**
     * 最大堆排序
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        BigHeap bigHeap = new BigHeap(arr.length);

        for (int i = 0; i < arr.length; i++) {
            bigHeap.insert(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = bigHeap.remove();
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
