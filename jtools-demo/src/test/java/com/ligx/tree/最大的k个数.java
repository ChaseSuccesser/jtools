package com.ligx.tree;

/**
 * @author: ligongxing.
 * @date: 2021年04月16日.
 */
public class 最大的k个数 {

    private static void findMaxK(int[] arr, int k) {
        SmallHeap smallHeap = new SmallHeap(k);

        for (int i = 0; i < k; i++) {
            smallHeap.insert(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > smallHeap.peekRoot()) {
                smallHeap.remove();
                smallHeap.insert(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.println(smallHeap.remove());
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 5};
        findMaxK(arr, 2);
    }
}
