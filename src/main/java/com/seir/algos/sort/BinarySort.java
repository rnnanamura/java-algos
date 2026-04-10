package com.seir.algos.sort;

import java.util.ArrayList;
import java.util.List;

public class BinarySort {

    public static List<String> sort(List<String> listToBeSorted) {
        if (listToBeSorted == null) {
            return new ArrayList<>();
        }
        if (listToBeSorted.isEmpty() || listToBeSorted.size() == 1) {
            return listToBeSorted;
        }
        int middle = listToBeSorted.size() / 2;
        List<String> left = sort(listToBeSorted.subList(0, middle));
        List<String> right = sort(listToBeSorted.subList(middle, listToBeSorted.size()));
        return merge(left, right);
    }

    static List<String> merge(List<String> left, List<String> right) {
        if (left == null || left.isEmpty()) {
            return right;
        }
        if (right == null || right.isEmpty()) {
            return left;
        }
        int total = left.size() + right.size();
        List<String> result = new ArrayList<>(total);
        int leftIndex =0;
        int rightIndex = 0;
        for (int i = 0; i< total; i++) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0) {
                result.add(left.get(leftIndex));
                if (leftIndex < left.size() - 1) {
                    leftIndex++;

                }
            } else {
                result.add(right.get(rightIndex));
                if (rightIndex < right.size() - 1) {
                    rightIndex++;
                }
            }
        }
        return result;
    }
}
