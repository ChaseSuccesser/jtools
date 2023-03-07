package com.ligx.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: ligongxing.
 * @date: 2023年03月07日.
 */
public class MedianFinder {

    PriorityQueue<Integer> ascQueue;
    PriorityQueue<Integer> descQueue;

    public MedianFinder() {
        ascQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        descQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        if (descQueue.isEmpty() || num <= descQueue.peek()) {
            descQueue.offer(num);
            if (ascQueue.size() + 1 < descQueue.size()) {
                ascQueue.offer(descQueue.poll());
            }
        } else {
            ascQueue.offer(num);
            if (ascQueue.size() > descQueue.size()) {
                descQueue.offer(ascQueue.poll());
            }
        }
    }

    public double findMedian() {
        if ((ascQueue.size() + descQueue.size()) % 2 == 0) {
            return (ascQueue.peek() + descQueue.peek()) / 2.0;
        } else {
            return descQueue.peek();
        }
    }

}
