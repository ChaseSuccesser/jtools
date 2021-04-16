package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class 最小的k个数 {

    private static void findMinK(int[] arr, int k) {
        BigHeap bigHeap = new BigHeap(k);

        for (int i = 0; i < k; i++) {
            bigHeap.insert(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < bigHeap.peekRoot()) {
                bigHeap.remove();
                bigHeap.insert(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.println(bigHeap.remove());
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 5};
        findMinK(arr, 2);
    }
}
