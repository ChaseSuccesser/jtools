package com.ligx.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月07日.
 */
public class 滑动窗口的最大值 {

    private static int[] max(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{arr[i], i});
        }

        int[] resultArr = new int[arr.length - k + 1];
        resultArr[0] = queue.peek()[0];
        int j = 1;

        for (int i = k; i < arr.length; i++) {
            queue.offer(new int[]{arr[i], i});
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            resultArr[j++] = queue.peek()[0];
        }
        return resultArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] resultArr = max(arr, 3);
        for (int i = 0; i < resultArr.length; i++) {
            System.out.print(resultArr[i] + " ");
        }
    }
}
