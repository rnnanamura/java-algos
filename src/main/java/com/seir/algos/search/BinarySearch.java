package com.seir.algos.search;

import java.util.List;

public class BinarySearch {

    public static final int NOT_FOUND = -1;
    public static int searchString(String value, List<String> list) {
        int result = NOT_FOUND;
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

        return result;
    }
    public static int searchOrInsertionString(String value, List<String> list) {
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
    public static int lowerBoundSearch(String value, List<String> list) {
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
    public static int upperBoundSearch(String value, List<String> list) {
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
    public static int minCutHeight(List<Integer> heights, int targetCumulation) {
        if (targetCumulation < 0 || heights == null || heights.isEmpty()) {
            return -1;
        }
        int minHeight = heights.stream().min(Integer::compareTo).orElse(0);
        int maxheight = heights.stream().max(Integer::compareTo).orElse(1000);
        while (minHeight <= maxheight) {
            int mediumHeight = (minHeight + maxheight) / 2;
            int cumulation = cumulation(heights, mediumHeight);
            if (cumulation == targetCumulation) {
                return mediumHeight;
            } else if (cumulation < targetCumulation) {
                maxheight = mediumHeight - 1;
            } else {
                minHeight = mediumHeight + 1;
            }
        }
        return maxheight;
    }

    private static int cumulation(List<Integer> heights, int height) {
        return heights.stream().map(h -> h > height ? h - height: 0).reduce(0, Integer::sum);
    }

    /**
     * Search for a target integer value in a rotated sorted array.
     * @param values - sorted array and then rotated
     * @param target - value to search
     * @return - index of the target value in the array, -1 if not found
     */
    public static int searchInRotatedSortedArray(List<Integer> values, int target) {
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
            if (values.get(medium) == target) {
                return medium;
            } else if (values.get(medium) < values.get(right)) {
                if (values.get(medium) < target && target <= values.get(right)) {
                    left = medium + 1;
                    continue;
                } else {
                    right = medium - 1;
                    continue;
                }
            } else if (values.get(medium) > values.get(left)) {
                if (values.get(medium) > target && target >= values.get(left)) {
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
