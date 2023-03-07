package com.ligx.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月07日.
 */
public class 滑动窗口的最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }

        int[] maxNums = new int[nums.length - k + 1];
        maxNums[0] = queue.peek()[0];
        int j = 1;

        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            maxNums[j++] = queue.peek()[0];
        }
        return maxNums;
    }
}
