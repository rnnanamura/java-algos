package com.seir.algos.sort;

import java.util.ArrayList;
import java.util.List;

public class GenericBinarySort {

    /* */
    public static <T extends Comparable<T>> List<T> sort(List<T> theList) {
        if (theList == null || theList.isEmpty()) {
            return new ArrayList<>();
        }
        if (theList.size() == 1) {
            return new ArrayList<>(theList);
        }
        int middle = theList.size() / 2;
        List<T> left = sort(theList.subList(0, middle));
        List<T> right = sort(theList.subList(middle, theList.size()));
        return sort_merge(left, right);
    }

    public static <T extends Comparable<T>> List<T> sort_merge(List<T> left, List<T> right) {
        if (left == null || left.isEmpty()) {
            return new ArrayList<>(right);
        }
        if (right == null || right.isEmpty()) {
            return new ArrayList<>(left);
        }
        int total = left.size() + right.size();
        List<T> result = new ArrayList<>(total);
        int leftIndex =0;
        int rightIndex = 0;
        for (int i = 0; i< total; i++) {
            if (left.get(leftIndex).compareTo((T) right.get(rightIndex)) <= 0) {
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
     /* */
}
