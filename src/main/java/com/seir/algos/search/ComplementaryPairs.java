package com.seir.algos.search;

import java.util.*;

public class ComplementaryPairs {
    public record Pair(int a, int b) {}
    public static List<Pair> findComplementaryPairsInSortedList(List<Integer> sortedList, int total) {
        if (sortedList == null || sortedList.isEmpty()) {
            return new ArrayList<>();
        }
        int low = 0;
        int high = sortedList.size() - 1;
        List<Pair> result = new ArrayList<>();
        while (low < high) {
            int currentSum = sortedList.get(low) + sortedList.get(high);
            if (currentSum == total) {
                result.add(new Pair(sortedList.get(low), sortedList.get(high)));
                low++;
                high--;
            } else if (currentSum < total) {
                low++;
            } else {
                high--;
            }
        }
        return result;
    }

    public static Map<Integer, Integer> findComplementaryPairsInUnsortedList(List<Integer> unsortedList, int total) {
        if (unsortedList == null || unsortedList.isEmpty()) {
            return new HashMap<>();
        }
        Map<Integer, Integer> result = new HashMap<>();
        Set<Integer> allNumbers = new HashSet<>(unsortedList.size());
        allNumbers.addAll(unsortedList);
        for (Integer value : unsortedList) {
            int complement = total - value;
            if (allNumbers.contains(complement) && !result.containsKey(complement) && complement != value) {
                result.put(value, complement);
            }
        }
        return result;
    }
}
