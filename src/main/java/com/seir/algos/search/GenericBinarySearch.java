package com.seir.algos.search;

import java.util.List;

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
}
