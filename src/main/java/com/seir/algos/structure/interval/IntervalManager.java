package com.seir.algos.structure.interval;

import com.seir.algos.sort.GenericBinarySort;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class IntervalManager {

    public static class Interval<T extends Comparable<T>> implements Comparable<Interval<T>> {
        @Getter
        private final T start;
        @Getter
        private final T end;
        public Interval(T start, T end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval<T> o) {
            if (o == null) {
                throw new NullPointerException();
            }
            if (this.start.compareTo(o.start) != 0) {
                return this.start.compareTo(o.start);
            }
            if (this.end.compareTo(o.end) != 0) {
                return this.end.compareTo(o.end);
            }
            return 0;
        }
    }

    public static <T extends Comparable<T>> List<Interval<T>> findOverlapIntervals(List<Interval<T>> intervals) {
        List<Interval<T>> result = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return result;
        }
        T start = null;
        T end = null;
        for (Interval<T> interval : intervals) {
            if (start == null) {
                start = interval.start;
            }
            if (end == null) {
                end = interval.end;
            }
            if (end.compareTo(interval.start) >= 0) {
                start = interval.start;
                end = end.compareTo(interval.end) > 0 ? interval.end : end;
            } else {
                result.add(new Interval<>(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        result.add(new Interval<>(start, end));
        return result;
    }

    public static <T extends Comparable<T>> List<Interval<T>> mergeIntervals(List<Interval<T>> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }
        if (intervals.size() == 1) {
            return new ArrayList<>(intervals);
        }
        List<Interval<T>> sortedIntervals = intervals.stream().sorted().toList();
        List<Interval<T>> mergedIntervals = new ArrayList<>();
        T currentStart = null;
        T currentEnd = null;
        for (Interval<T> interval : sortedIntervals) {
            if (currentStart == null) {
                currentStart = interval.start;
            }
            if (currentEnd == null) {
                currentEnd = interval.end;
            }
            if (currentEnd.compareTo(interval.start) >= 0) {
                currentEnd = currentEnd.compareTo(interval.end) > 0 ? currentEnd : interval.end;
            } else {
                mergedIntervals.add(new Interval<>(currentStart, currentEnd));
                currentStart = interval.start;
                currentEnd = interval.end;
            }
        }
        mergedIntervals.add(new Interval<>(currentStart, currentEnd));
        return mergedIntervals;
    }
    public static <T extends Comparable<T>> List<Interval<T>> mergeIntervals(List<Interval<T>> intervals1, List<Interval<T>> intervals2) {
        List<Interval<T>> sortedIntervals1 = sort(intervals1);
        List<Interval<T>> sortedIntervals2 = sort(intervals2);
        return mergeSortedIntervals(sortedIntervals1, sortedIntervals2);
    }

    public static <T extends Comparable<T>> List<Interval<T>> mergeSortedIntervals(List<Interval<T>> sortedIntervals1, List<Interval<T>> sortedIntervals2) {
        List<Interval<T>> mergedIntervals = new ArrayList<>();
        List<Interval<T>> otherIntervals = null;
        int i = 0;
        int j = 0;
        while (i < sortedIntervals1.size() && j < sortedIntervals2.size()) {
            T currentStart;
            T currentEnd;
            if (sortedIntervals1.get(i).start.compareTo(sortedIntervals2.get(j).start) <= 0) {
                currentStart = sortedIntervals1.get(i).start;
                currentEnd = sortedIntervals1.get(i).end;
                otherIntervals = sortedIntervals2;
                j = mergeWithInterval(currentStart, currentEnd, sortedIntervals2, j, mergedIntervals);
                i++;
            } else {
                currentStart = otherIntervals.get(j).start;
                currentEnd = otherIntervals.get(j).end;
                otherIntervals = sortedIntervals1;
                i = mergeWithInterval(currentStart, currentEnd, sortedIntervals1, i, mergedIntervals);
                j++;
            }
        }

        addRemainingIntervals(sortedIntervals1, mergedIntervals, i);
        addRemainingIntervals(sortedIntervals2, mergedIntervals, j);

        return mergedIntervals;
    }

    private static <T extends Comparable<T>> int mergeWithInterval(T currentStart, T currentEnd, List<Interval<T>> otherIntervals, int index, List<Interval<T>> mergedIntervals) {
        while (index < otherIntervals.size() && currentEnd.compareTo(otherIntervals.get(index).start) >= 0) {
            currentEnd = currentEnd.compareTo(otherIntervals.get(index).end) > 0 ? currentEnd: otherIntervals.get(index).end;
            index++;
        }
        mergedIntervals.add(new Interval<>(currentStart, currentEnd));
        return index;
    }

    private static <T extends Comparable<T>> void addRemainingIntervals(List<Interval<T>> mergedIntervals, List<Interval<T>> sortedIntervals,  int i) {
        if (i < sortedIntervals.size()) {
            for (int l = i; l < sortedIntervals.size(); l++) {
                T currentEnd = mergedIntervals.get(mergedIntervals.size() - 1).end;
                if (currentEnd.compareTo(sortedIntervals.get(l).start) < 0) {
                    mergedIntervals.add(sortedIntervals.get(l));
                }
            }
        }
    }

    public static <T extends Comparable<T>> List<Interval<T>> sort(List<Interval<T>> intervals) {
        return GenericBinarySort.sort(intervals);
    }

}
