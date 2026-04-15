package com.seir.algos.structure.interval;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntervalManagerTest {

    @Test
    public void testMergeSortedIntervals() {
        List<IntervalManager.Interval<Long>> intervals1 = List.of(new IntervalManager.Interval<>(1L, 3L), new IntervalManager.Interval<>(5L, 7L));
        List<IntervalManager.Interval<Long>> intervals2 = List.of(new IntervalManager.Interval<>(2L, 4L), new IntervalManager.Interval<>(6L, 8L));
        List<IntervalManager.Interval<Long>> mergedIntervals = IntervalManager.mergeSortedIntervals(intervals1, intervals2);
        assertEquals(2, mergedIntervals.size());
        assertEquals(1L, mergedIntervals.get(0).getStart());
        assertEquals(4L, mergedIntervals.get(0).getEnd());

        assertEquals(5L, mergedIntervals.get(1).getStart());
        assertEquals(8L, mergedIntervals.get(1).getEnd());
    }
    @Test
    public void testMergeSortedIntervals2() {
        List<IntervalManager.Interval<Long>> intervals1 = List.of(new IntervalManager.Interval<>(1L, 4L), new IntervalManager.Interval<>(5L, 7L));
        List<IntervalManager.Interval<Long>> intervals2 = List.of(new IntervalManager.Interval<>(2L, 4L), new IntervalManager.Interval<>(3L, 8L));
        List<IntervalManager.Interval<Long>> mergedIntervals = IntervalManager.mergeSortedIntervals(intervals1, intervals2);
        assertEquals(1, mergedIntervals.size());
        assertEquals(1L, mergedIntervals.get(0).getStart());
        assertEquals(8L, mergedIntervals.get(0).getEnd());

    }
    @Test
    public void testMergeSortedIntervals3() {
        List<IntervalManager.Interval<Long>> intervals1 = List.of(new IntervalManager.Interval<>(1L, 4L), new IntervalManager.Interval<>(5L, 7L));
        List<IntervalManager.Interval<Long>> intervals2 = List.of(new IntervalManager.Interval<>(2L, 4L), new IntervalManager.Interval<>(3L, 8L), new IntervalManager.Interval<>(9L, 12L));
        List<IntervalManager.Interval<Long>> mergedIntervals = IntervalManager.mergeSortedIntervals(intervals1, intervals2);
        assertEquals(2, mergedIntervals.size());
        assertEquals(1L, mergedIntervals.get(0).getStart());
        assertEquals(8L, mergedIntervals.get(0).getEnd());

    }

    @Test
    public void testMergeIntervals() {
        List<IntervalManager.Interval<Long>> intervals = List.of(
                new IntervalManager.Interval<>(2L, 6L),
                new IntervalManager.Interval<>(3L, 5L),
                new IntervalManager.Interval<>(9L, 12L));
        List<IntervalManager.Interval<Long>> mergedIntervals = IntervalManager.mergeIntervals(intervals);
        assertEquals(2, mergedIntervals.size());
        assertEquals(2L, mergedIntervals.get(0).getStart());
        assertEquals(6L, mergedIntervals.get(0).getEnd());
        assertEquals(9L, mergedIntervals.get(1).getStart());
        assertEquals(12L, mergedIntervals.get(1).getEnd());

        intervals = List.of(
                new IntervalManager.Interval<>(2L, 6L),
                new IntervalManager.Interval<>(3L, 5L),
                new IntervalManager.Interval<>(4L, 12L));
        mergedIntervals = IntervalManager.mergeIntervals(intervals);
        assertEquals(1, mergedIntervals.size());
        assertEquals(2L, mergedIntervals.get(0).getStart());
        assertEquals(12L, mergedIntervals.get(0).getEnd());

        intervals = List.of(
                new IntervalManager.Interval<>(3L, 5L),
                new IntervalManager.Interval<>(2L, 6L),
                new IntervalManager.Interval<>(4L, 12L));
        mergedIntervals = IntervalManager.mergeIntervals(intervals);
        assertEquals(1, mergedIntervals.size());
        assertEquals(2L, mergedIntervals.get(0).getStart());
        assertEquals(12L, mergedIntervals.get(0).getEnd());
    }
}
