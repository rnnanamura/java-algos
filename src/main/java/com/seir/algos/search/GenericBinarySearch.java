package com.seir.algos.search;

import java.util.List;
import java.util.function.BiFunction;

public class GenericBinarySearch {
    public static final int NOT_FOUND = -1;
    public static <T extends Comparable<T>> int search(T value, List<T> list) {
        int result = NOT_FOUND;
        if (list == null || list.isEmpty()) {
            return result;
        }
        int medium = list.size() / 2;
        int low = 0;
        int high = list.size() -1;
        while (low <= high) {
            if (list.get(medium).equals(value)) {
                return medium;
            }
            if (list.get(medium).compareTo(value) > 0) {
                high = medium - 1;
            } else {
                low = medium + 1;
            }
            medium = (low + high) / 2;
        }
        return result;
    }
    public static <T extends Comparable<T>> int searchOrInsertion(T value, List<T> list) {
        int result = -1;
        if (list == null || list.isEmpty()) {
            return result;
        }

        int medium = list.size() / 2;
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            if (list.get(medium).equals(value)) {
                return medium;
            }
            if (list.get(medium).compareTo(value) > 0) {
                high = medium - 1;
            } else {
                low = medium + 1;
            }
            medium = (low + high) / 2;
        }

        return high+1;
    }
    public static <T extends Comparable<T>> int lowerBoundSearch(T value, List<T> list) {
        int result = NOT_FOUND;
        if (list == null || list.isEmpty()) {
            return result;
        }
        int medium = list.size() / 2;
        int low = 0;
        int high = list.size() - 1;
        while (low < high) {
            if (list.get(medium).equals(value)) {
                high = medium;
            } else if (list.get(medium).compareTo(value) > 0) {
                high = medium - 1;
            } else {
                low = medium + 1;
            }
            medium = (low + high) / 2;
        }
        return list.get(low).equals(value)? low : NOT_FOUND;
    }
    public static <T extends Comparable<T>> int upperBoundSearch(T value, List<T> list) {
        int result = NOT_FOUND;
        if (list == null || list.isEmpty()) {
            return result;
        }
        int low = 0;
        int high = list.size() - 1;
        while (low < high) {
            int medium = ((low + high) / 2 )  + 1;
            if (list.get(medium).equals(value)) {
                low = medium;
            } else if (list.get(medium).compareTo(value) > 0) {
                high = medium - 1;
            } else {
                low = medium + 1;
            }
        }
        return list.get(high).equals(value)? high : NOT_FOUND;
    }

    public static <T extends Comparable<T>> int minAccumulation(List<T> heights, int targetCumulation, int minHeight, int maxHeight,
                                                                BiFunction<List<T>, Integer, Integer> accumulationFunction) {
        if (targetCumulation < 0 || heights == null || heights.isEmpty()) {
            return -1;
        }
        while (minHeight <= maxHeight) {
            int mediumHeight = (minHeight + maxHeight) / 2;
            int cumulation = accumulationFunction.apply(heights, mediumHeight);
            if (cumulation == targetCumulation) {
                return mediumHeight;
            } else if (cumulation < targetCumulation) {
                maxHeight = mediumHeight - 1;
            } else {
                minHeight = mediumHeight + 1;
            }
        }
        return maxHeight;
    }
    /**
     * Search for a target integer value in a rotated sorted array.
     * @param values - sorted array and then rotated
     * @param target - value to search
     * @return - index of the target value in the array, -1 if not found
     */
    public static  <T extends Comparable<T>> int searchInRotatedSortedArray(List<T> values, T target) {
        if (values == null || values.isEmpty()) {
            return NOT_FOUND;
        }
        int left = 0;
        int right = values.size() - 1;
        while (left < right) {
            /*if (values.get(left) == target) {
                return left;
            }
            if (values.get(right) == target) {
                return right;
            }*/
            int medium = (left + right) / 2;
            if (values.get(medium).equals(target)) {
                return medium;
            } else if (values.get(medium).compareTo(values.get(right)) < 0) {
                if (values.get(medium).compareTo(target) < 0 && target.compareTo(values.get(right)) <= 0 ) {
                    left = medium + 1;
                    continue;
                } else {
                    right = medium - 1;
                    continue;
                }
            } else if (values.get(medium).compareTo(values.get(left)) > 0) {
                if (values.get(medium).compareTo(target) > 0 && target.compareTo(values.get(left)) >= 0) {
                    right = medium - 1;
                    continue;
                } else {
                    left = medium + 1;
                    continue;
                }
            }
        }
        return values.get(left).equals(target)? left : NOT_FOUND;
    }
}
