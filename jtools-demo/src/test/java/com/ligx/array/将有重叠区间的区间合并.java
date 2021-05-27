package com.ligx.array;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * @author: ligongxing.
 * @date: 2021年05月27日.
 */
public class 将有重叠区间的区间合并 {

    private static void mergeRange(Vector<Range> vector) {
        if (vector == null || vector.size() == 0) {
            return;
        }
        Collections.sort(vector, new RangeComparator());

        int curr = 0;
        while (curr < vector.size() - 1) {
            if (vector.get(curr).getEnd() > vector.get(curr + 1).getStart()) {
                int start = vector.get(curr).getStart();
                int end = Math.max(vector.get(curr).getEnd(), vector.get(curr + 1).getEnd());
                vector.remove(curr);
                vector.remove(curr);
                vector.add(curr, new Range(start, end));
            } else {
                curr++;
            }
        }
    }

    public static void main(String[] args) {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(2, 6);
        Range range3 = new Range(8, 10);
        Range range4 = new Range(15, 18);
        Vector<Range> vector = new Vector<>();
        vector.add(range1);
        vector.add(range2);
        vector.add(range3);
        vector.add(range4);
        mergeRange(vector);

        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i).getStart() + " " + vector.get(i).getEnd());
        }
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