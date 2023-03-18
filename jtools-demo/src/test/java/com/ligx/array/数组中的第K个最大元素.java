package com.ligx.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月18日.
 */
public class 数组中的第K个最大元素 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }

        return queue.peek();
    }

}
