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
}
