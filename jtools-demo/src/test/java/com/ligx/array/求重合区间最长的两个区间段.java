package com.ligx.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: ligongxing.
 * Date: 2021年04月24日.
 */
public class 求重合区间最长的两个区间段 {

    private static void maxShareRange(Range[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.sort(arr, new RangeComparator());

        Range pre = arr[0];
        int maxLen = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].getStart() > pre.getEnd()) {
                pre = arr[i];
            } else {
                if (arr[i].getEnd() > pre.getEnd()) {
                    maxLen = Math.max(maxLen, pre.getEnd() - arr[i].getStart());
                    pre = arr[i];
                } else {
                    maxLen = Math.max(maxLen, arr[i].getEnd() - arr[i].getStart());
                }
            }
        }
        System.out.println("maxLen:" + maxLen);
    }


    public static void main(String[] args) {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(2, 8);
        Range range3 = new Range(5, 9);
        Range[] arr = {range1, range2, range3};
        maxShareRange(arr);
    }

    static class RangeComparator implements Comparator<Range> {
        @Override
        public int compare(Range o1, Range o2) {
            return o1.getStart() - o2.getStart();
        }
    }

    static class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}


