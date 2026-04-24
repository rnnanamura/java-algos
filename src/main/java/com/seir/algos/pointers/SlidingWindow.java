package com.seir.algos.pointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindow {

    public interface WindowProcessing<T,U> {
        T process(List<U> listValue);
    }

    public interface WindowCondition<T,U> {
        boolean condition(List<U> listValue);
    }

    public interface Consolidate<T> {
        T consolidate(T result, T value);
    }

    public static <T,U> T processUsingFixedWindow(List<U> listValue, int windowSize, T initialValue, Consolidate<T> consolidate, WindowProcessing<T,U> windowProcessing) {
        int start = 0;
        int end = 0;
        T result = initialValue;
        while (end < listValue.size()) {
            if (end - start + 1 == windowSize) {
                result = consolidate.consolidate(result, windowProcessing.process(listValue.subList(start, end + 1)));
                start++;
            }
            end++;
        }
        return result;
    }

    public static <T, U> T processUsingDynamicWindow(List<U> listValue, WindowCondition<T,U> windowCondition, WindowProcessing<T,U> windowProcessing) {
        int start = 0;
        int end = 0;
        T result = null;
        while (end < listValue.size()) {
            while (!windowCondition.condition(listValue.subList(start, end + 1))) {
                start++;
            }
            result = windowProcessing.process(listValue.subList(start, end + 1));
            end++;
        }

        return result;
    }

    public static int numberOfAnagrams(String anagram, String value) {
        Map<Character, Integer> anagramMap = new HashMap<>();
        for (char c: anagram.toCharArray()) {
            anagramMap.merge(c, 1, Integer::sum);
        }
        int windowSize = anagram.length();
        List<Character> charList = new ArrayList<>();
        for (char c : value.toCharArray()) charList.add(c);
        return SlidingWindow.<Integer,Character>processUsingFixedWindow(charList, windowSize, 0,
                Integer::sum,
                (list) -> {
                    Map<Character, Integer> valueMap = new HashMap<>();
                    for (Character c: list) {
                        valueMap.put(c, valueMap.getOrDefault(c, 0) + 1);
                    }
                    return anagramMap.equals(valueMap)? 1 : 0;
                });
    }
}
